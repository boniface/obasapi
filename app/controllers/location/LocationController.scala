package controllers.location

import controllers.ApiResponse
import domain.location.Location
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.{Logger, Logging}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.location.LocationService
import services.login.LoginService
import util.HelperUtil

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class LocationController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {
  type DomainObject = Location
  def className: String = "LocationController"
  override val logger: Logger = Logger(className)
  def domainService: LocationService = LocationService.roach
  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Create request with body: " + entity)
      println("Create request with body: " + entity)
      entity match {
        case Right(value) =>
          val copy = value.copy(locationId = HelperUtil.codeGen(value.name + value.locationTypeId))
          logger.info("Saving location: " + copy)
          println("Saving location: " + copy)
          val response: Future[Option[Location]] = for {
            results: Option[Location] <- domainService.saveEntity(copy)
          } yield results
          api.requestResponse[Option[Location]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }
  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Update request with body: " + entity)
      entity match {
        case Right(value) =>
          val response: Future[Option[Location]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[Location] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[Location]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def getLocationById(locationId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by id: " + locationId)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(locationId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getLocationsForParent(locationParentId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by locationParentId: " + locationParentId)
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntitiesForParent(locationParentId)
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def getAllLocation: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve all requested")
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def getParentLocations: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve all parent locations requested")
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getParentEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteLocation: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Delete request with body: " + entity)
      entity match {
        case Right(value) =>
          val response: Future[Boolean] = for {
            results: Boolean <- domainService.deleteEntity(value)
          } yield results
          api.requestResponse[Boolean](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }
}

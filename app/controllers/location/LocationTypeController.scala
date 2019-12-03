package controllers.location

import controllers.ApiResponse
import domain.location.LocationType
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.{Logger, Logging}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.location.LocationTypeService
import services.login.LoginService
import util.HelperUtil

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class LocationTypeController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {
  type DomainObject = LocationType
  def className: String = "LocationTypeController"
  override val logger: Logger = Logger(className)
  def domainService: LocationTypeService = LocationTypeService.roach
  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Create request with body: " + entity)
      println("Create request with body: " + entity)
      entity match {
        case Right(value) =>
          val generatedCode = HelperUtil.codeGen(value.name)
          val copy = value.copy(locationTypeId = generatedCode, code = generatedCode)
          logger.info("Saving location type: " + copy)
          println("Saving location type: " + copy)
          val response: Future[Option[LocationType]] = for {
            results: Option[LocationType] <- domainService.saveEntity(copy)
          } yield results
          api.requestResponse[Option[LocationType]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Update request with body: " + entity)
      entity match {
        case Right(value) =>
          val response: Future[Option[LocationType]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[LocationType] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[LocationType]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }


  def getLocationTypeById(locationTypeId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by id: " + locationTypeId)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(locationTypeId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllLocationType: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve all requested")
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteLocationType: Action[JsValue] = Action.async(parse.json) {
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

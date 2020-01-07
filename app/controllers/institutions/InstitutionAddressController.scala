package controllers.institutions

import controllers.ApiResponse
import domain.institutions.InstitutionAddress
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.libs.json.{JsValue, Json}
import play.api.{Logger, Logging}
import play.api.mvc._
import services.institutions.InstitutionAddressService
import services.login.LoginService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class InstitutionAddressController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {

  type DomainObject = InstitutionAddress

  def className: String = "InstitutionAddressController"
  override val logger: Logger = Logger(className)

  def domainService: InstitutionAddressService = InstitutionAddressService.apply

  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Create request with body: " + entity)
      entity match {
        case Right(value) =>
          val response: Future[Option[DomainObject]] = for {
            results: Option[DomainObject] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[DomainObject]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Update request with body: " + entity)
      entity match {
        case Right(value) =>
          val response: Future[Option[DomainObject]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[DomainObject] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[DomainObject]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def read(institutionId: String, addressTypeId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by institutionId: " + institutionId + " and addressTypeId: " + addressTypeId)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(institutionId, addressTypeId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getInstitutionAdresses(institutionId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by institutionId: " + institutionId)
      println("Retrieve by institutionId: " + institutionId)
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntitiesForInstitution(institutionId)
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def getAll: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve all requested")
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def delete: Action[JsValue] = Action.async(parse.json) {
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

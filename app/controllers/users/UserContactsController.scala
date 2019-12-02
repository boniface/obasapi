package controllers.users

import controllers.ApiResponse
import domain.users.UserContacts
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.{Logger, Logging}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.login.LoginService
import services.users.UserContactsService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserContactsController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {
  type DomainObject = UserContacts

  def className: String = "UserContactsController"
  override val logger: Logger = Logger(className)

  def domainService: UserContactsService = UserContactsService.roach
  def loginService: LoginService = LoginService.apply


  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Create request with body: " + entity)
      entity match {
        case Right(value) =>
          val response: Future[Option[UserContacts]] = for {
            results: Option[UserContacts] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[UserContacts]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Update request with body: " + entity)
      entity match {
        case Right(value) =>
          val response: Future[Option[UserContacts]] = for {
//            _ <- loginService.checkLoginStatus(request)
            results: Option[UserContacts] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[UserContacts]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def getUserContacts(userId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by userId: " + userId)
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntityForUser(userId)
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def getUserContactsById(userId: String, contactTypeId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by userId: " + userId + " and contactTypeId: " + contactTypeId)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(userId, contactTypeId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllUserContacts: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve all requested")
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteUserContacts: Action[JsValue] = Action.async(parse.json) {
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

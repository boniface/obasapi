package controllers.users

import controllers.ApiResponse
import domain.users.UserApplication
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.{Logger, Logging}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.login.LoginService
import services.users.UserApplicationService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserApplicationController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {
  type DomainObject = UserApplication
  def className: String = "UserApplicationController"
  override val logger: Logger = Logger(className)
  def domainService: UserApplicationService = UserApplicationService.roach
  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Create request with body: " + entity)
      println("Create request with body: " + entity)
      entity match {
        case Right(value) =>
          val response: Future[Option[DomainObject]] = for {
            results: Option[UserApplication] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[DomainObject]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def read(userId: String, applicationId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by userId: " + userId + " and applicationId: " + applicationId)
      println("Retrieve by userId: " + userId + " and applicationId: " + applicationId)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(userId, applicationId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getEntitiesForUser(userId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by userId: " + userId)
      println("Retrieve by userId: " + userId)
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntitiesForUser(userId)
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def getEntityForApplication(applicationId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by applicationId: " + applicationId)
      println("Retrieve by applicationId: " + applicationId)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntityForApplication(applicationId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getLatestForUser(userId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve latest for userId: " + userId)
      println("Retrieve latest for userId: " + userId)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getLatestEntityForUser(userId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAll: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve all requested")
      println("Retrieve all requested")
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def delete: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Delete request with body: " + entity)
      println("Delete request with body: " + entity)
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

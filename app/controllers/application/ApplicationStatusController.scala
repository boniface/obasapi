package controllers.application

import controllers.ApiResponse
import domain.application.ApplicationStatus
import io.circe.generic.auto._
import javax.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import play.api.{Logger, Logging}
import services.application.ApplicationStatusService
import services.login.LoginService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApplicationStatusController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {

  type DomainObject = ApplicationStatus
  def className: String = "UserApplicationStatusController"
  override val logger: Logger = Logger(className)
  def domainService: ApplicationStatusService = ApplicationStatusService.roach
  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Create request with body: " + entity)
      println("Create request with body: " + entity)
      entity match {
        case Right(value) =>
          val response: Future[Option[DomainObject]] = for {
            results: Option[DomainObject] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[DomainObject]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def getLatestForAppnStatus(applicationId: String, statusId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve latest by applicationId: " + applicationId + " and statusId: " + statusId)
      println("Retrieve latest by applicationId: " + applicationId + " and statusId: " + statusId)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getLatestForAppnStatus(applicationId, statusId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getLatestForApplication(applicationId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve latest by applicationId: " + applicationId)
      println("Retrieve latest by applicationId: " + applicationId)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getLatestForApplication(applicationId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getEntitiesForApplication(applicationId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by applicationId: " + applicationId)
      println("Retrieve by applicationId: " + applicationId)
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntitiesForApplication(applicationId)
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
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

  def checkIfApplicationComplete(applicationId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Check if application is complete using applicationId: " + applicationId)
      println("Check if application is complete using applicationId: " + applicationId)
      val response: Future[Boolean] = for {
        results <- domainService.checkIfCompleted(applicationId)
      } yield results
      api.requestResponse[Boolean](response, className)
  }
}

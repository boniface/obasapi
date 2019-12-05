package controllers.documents

import controllers.ApiResponse
import domain.documents.DocumentStatus
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.{Logger, Logging}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.documents.DocumentStatusService
import services.login.LoginService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class DocumentStatusController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {

  type DomainObject = DocumentStatus
  def className: String = "DocumentStatusController"
  override val logger: Logger = Logger(className)
  def domainService: DocumentStatusService = DocumentStatusService.apply
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

  def getLatestForDocumentnStatus(documentId: String, statusId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve latest by documentId: " + documentId + " and statusId: " + statusId)
      println("Retrieve latest by documentId: " + documentId + " and statusId: " + statusId)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getLatestForDocumentnStatus(documentId, statusId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getLatestForDocument(documentId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve latest by documentId: " + documentId)
      println("Retrieve latest by documentId: " + documentId)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getLatestForDocument(documentId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getEntitiesForDocument(documentId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by documentId: " + documentId)
      println("Retrieve by documentId: " + documentId)
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntitiesForDocument(documentId)
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

}

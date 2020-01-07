package controllers.documents

import controllers.ApiResponse
import domain.documents.Document
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.{Logger, Logging}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.documents.DocumentService
import services.login.LoginService
import util.APPKeys

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class DocumentController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {
  type DomainObject = Document
  def className: String = "DocumentController"
  override val logger: Logger = Logger(className)
  def domainService: DocumentService = DocumentService.roach
  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Create request with body: " + entity)
      println("Create request with body: " + entity)
      entity match {
        case Right(value) =>
          val copy = value.copy(documentStatus = APPKeys.PROCESSING)
          logger.info("Document to save: " + copy)
          println("Document to save: " + copy)
          val response: Future[Option[Document]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[Document] <- domainService.saveEntity(copy)
          } yield results
          api.requestResponse[Option[Document]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Update request with body: " + entity)
      entity match {
        case Right(value) =>
          val response: Future[Option[Document]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[Document] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[Document]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }


  def getDocumentById(email: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(email)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllDocument: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteDocument: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
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

package controllers.documents



import controllers.ApiResponse
import domain.documents.DocumentType
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.{Logger, Logging}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.address.AddressTypeService
import services.application.ApplicantTypeService
import services.application.Impl.cockroachdb.ApplicantTypeServiceImpl
import services.demographics.Impl.cockroachdb.RaceServiceImpl
import services.demographics.{RaceService, RoleService}
import services.documents.Impl.cockroachdb.DocumentTypeServiceImpl
import services.documents.{DocumentService, DocumentTypeService}
import services.login.LoginService
import util.HelperUtil

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future



class DocumentTypeController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {
  type DomainObject = DocumentType

  def className: String = "DocumentTypeController"
  override val logger: Logger = Logger(className)

  def domainService: DocumentTypeService = DocumentTypeService.roach

  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Create request with body: " + entity)
      println("Create request with body: " + entity)
      entity match {
        case Right(value) =>
          val copy = value.copy(documentTypeId = HelperUtil.codeGen(value.documentTypename))
          logger.info("Saving address type: " + copy)
          println("Saving address type: " + copy)
          val response: Future[Option[DocumentType]] = for {
            results: Option[DocumentType] <- domainService.saveEntity(copy)
          } yield results
          api.requestResponse[Option[DocumentType]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Update request with body: " + entity)
      println("Update request with body: " + entity)
      entity match {
        case Right(value) =>
          val response: Future[Option[DocumentType]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[DocumentType] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[DocumentType]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }


  def getDocumentTypeById(documentTypeId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by id: " + documentTypeId)
      println("Retrieve by id: " + documentTypeId)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(documentTypeId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllDocumentType: Action[AnyContent] = Action.async {
    logger.info("Retrieve all requested")
    println("Retrieve all requested")
    implicit request: Request[AnyContent] =>
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteDocumentType: Action[JsValue] = Action.async(parse.json) {
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

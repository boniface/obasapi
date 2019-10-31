package controllers.users

import controllers.ApiResponse
import domain.users.UserDocument
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.login.LoginService
import services.users.UserDocumentService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserDocumentController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc){
  type DomainObject = UserDocument

  def className: String = "UserDocumentsController"
  def domainService: UserDocumentService = UserDocumentService.roach
  def loginService: LoginService = LoginService.apply


  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[UserDocument]] = for {
            results: Option[UserDocument] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[UserDocument]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[UserDocument]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[UserDocument] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[UserDocument]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def getUserDocuments(userId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getUserDocuments(userId)
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def getUserDocument(userId: String, documentId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(userId, documentId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllUsersDocuments: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteUserDocuments: Action[JsValue] = Action.async(parse.json) {
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

package controllers.users

import controllers.ApiResponse
import domain.users.UserResults
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.login.LoginService
import services.users.UserResultsService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserResultsController  @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc){
  type DomainObject = UserResults

  def className: String = "UserResultsController"
  def domainService: UserResultsService = UserResultsService.roach
  def loginService: LoginService = LoginService.apply


  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[UserResults]] = for {
            results: Option[UserResults] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[UserResults]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[UserResults]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[UserResults] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[UserResults]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def getUserResultsById(userResultsId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(userResultsId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllUserResults: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteUserResults: Action[JsValue] = Action.async(parse.json) {
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

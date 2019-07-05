package controllers.login

import controllers.ApiResponse
import domain.login.{LoginCredential, Profile}
import domain.mail.MessageResponse
import domain.users.User
import domain.util.events.Events
import domain.util.login.UserGeneratedToken
import io.circe.generic.auto._
import javax.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services.login.LoginService
import services.users.UserService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class LoginController @Inject()
(cc: ControllerComponents,api: ApiResponse) extends AbstractController(cc) {
  def className: String = "LoginController"
  def domainService: LoginService = LoginService.apply
  def userService: UserService = UserService.roach

  type DomainObject = MessageResponse

  def isUserRegistered: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[User](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Boolean] = for {
            results: Boolean <- domainService.isUserRegistered(value)
          } yield results
          api.requestResponse[Boolean](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def isUserAvailable(siteId: String, email:String) :Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent]=>
      val response: Future[Boolean] = for {
        results <- userService.isUserAvailable(siteId,email)
      } yield results
      api.requestResponse[Boolean](response, className)
  }

  def forgotPassword: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[Profile](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[DomainObject] = for {
            results <- domainService.forgotPassword(value)
          } yield results
          api.requestResponse[DomainObject](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }


  def getLoginToken: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[LoginCredential](request.body).asEither
      entity match {
        case Right(value) =>
          val agent = request.headers.get(Events.BROWSER_AGENT).getOrElse("")
          val response: Future[UserGeneratedToken] = for {
            results <- domainService.getLoginToken(value, agent)
          } yield results
          api.requestResponse[UserGeneratedToken](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def resetPasswordRequest(resetKey: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[MessageResponse] = for {
        results: MessageResponse <- domainService.resetPasswordRequest(resetKey)
      } yield results
      api.requestResponse[MessageResponse](response, className)
  }

}

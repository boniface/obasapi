package controllers.login

import controllers.ApiResponse
import domain.login.{Login, LoginToken, Register}
import io.circe.generic.auto._
import javax.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services.login.LoginService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class LoginController @Inject()
(cc: ControllerComponents,api: ApiResponse) extends AbstractController(cc) {
  def className: String = "LoginController"

  def domainService: LoginService = LoginService.apply

  def login: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[Login](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[LoginToken]] = for {
            results <- domainService.getLoginToken(value)
          } yield results
          api.requestResponse[Option[LoginToken]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def isUserRegistered: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[Register](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Boolean] = for {
            results <- domainService.isUserRegistered(value)
          } yield results
          api.requestResponse[Boolean](response, className)
        case Left(error) => api.errorResponse(error,className)
      }
  }

  def forgotPassword: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[Register](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Boolean] = for {
            results <- domainService.forgotPassword(value)
          } yield results
          api.requestResponse[Boolean](response, className)
        case Left(error) => api.errorResponse(error,className)
      }
  }

  def register: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[Register](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Boolean] = for {
            results <- domainService.register(value)
          } yield results
          api.requestResponse[Boolean](response, className)
        case Left(error) => api.errorResponse(error,className)
      }
  }

  def resetPassword(resetKey: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response= for {
        results: Boolean <- domainService.resetPasswordRequest(resetKey)
      } yield results
      api.requestResponse[Boolean](response,className)
  }

}

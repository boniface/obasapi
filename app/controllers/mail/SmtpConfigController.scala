package controllers.mail

import controllers.ApiResponse
import domain.mail.SmtpConfig
import io.circe.generic.auto._
import javax.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services.login.LoginService
import services.mail.SmtpConfigService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class SmtpConfigController @Inject()
( api: ApiResponse, cc: ControllerComponents) extends AbstractController(cc)  {
  def className: String ="SmtpConfigController"
  def domainService: SmtpConfigService = SmtpConfigService.roach
  def loginService: LoginService = LoginService.apply
  type DomainObject = SmtpConfig

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Boolean] = for {
            _ <- loginService.loginStatus(request)
            results: Boolean <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Boolean](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Boolean] = for {
            _ <- loginService.loginStatus(request)
            results: Boolean <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Boolean](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def getEntity(id: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent]=>
      val response: Future[Option[DomainObject]] = for {
        _ <- loginService.loginStatus(request)
        results <- domainService.getEntity(id)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getEntities: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent]=>
      val response: Future[Seq[DomainObject]] = for {
        _ <- loginService.loginStatus(request)
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteEntity: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Boolean] = for {
            _ <- loginService.loginStatus(request)
            results: Boolean <- domainService.deleteEntity(value)
          } yield results
          api.requestResponse[Boolean](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

}

package controllers.users

import controllers.ApiResponse
import domain.users.UserDemographics
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.login.LoginService
import services.users.UserDemographicsService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserDemographicsController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc){
  type DomainObject = UserDemographics

  def className: String = "UserDemographicsController"
  def domainService: UserDemographicsService = UserDemographicsService.roach
  def loginService: LoginService = LoginService.apply


  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[UserDemographics]] = for {
            results: Option[UserDemographics] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[UserDemographics]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[UserDemographics]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[UserDemographics] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[UserDemographics]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def getUserDemographicsById(userDemographicsId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(userDemographicsId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllUserDemographics: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteUserDemographics: Action[JsValue] = Action.async(parse.json) {
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
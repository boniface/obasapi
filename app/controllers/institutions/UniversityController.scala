package controllers.institutions

import controllers.ApiResponse
import domain.institutions.University
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.institutions.UniversityService
import services.login.LoginService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UniversityController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) {
  type DomainObject = University

  def className: String = "UniversityController"

  def domainService: UniversityService = UniversityService.roach

  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[University]] = for {
            results: Option[University] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[University]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[University]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[University] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[University]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }


  def getUniversityById(universityId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(universityId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllUniversity: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteUniversity: Action[JsValue] = Action.async(parse.json) {
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

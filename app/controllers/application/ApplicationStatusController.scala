package controllers.application


import controllers.ApiResponse
import domain.application.ApplicationStatus
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.address.AddressTypeService
import services.application.Impl.cockroachdb.ApplicationStatusServiceImpl
import services.application.{ApplicantTypeService, ApplicationStatusService}
import services.demographics.RoleService
import services.login.LoginService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future



class ApplicationStatusController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) {
  type DomainObject = ApplicationStatus

  def className: String = "ApplicationStatusController"

  def domainService: ApplicationStatusService = ApplicationStatusService.roach
  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[ApplicationStatus]] = for {
            results: Option[ApplicationStatus] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[ApplicationStatus]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }
  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[ApplicationStatus]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[ApplicationStatus] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[ApplicationStatus]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }



  def getApplicationStatusById(applicationStatusId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(applicationStatusId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllApplicationStatus: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteApplicationStatus: Action[JsValue] = Action.async(parse.json) {
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

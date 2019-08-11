package controllers.subjects



import controllers.ApiResponse
import domain.subjects.MatricSubjects
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.address.AddressTypeService
import services.application.ApplicantTypeService
import services.application.Impl.cockroachdb.ApplicantTypeServiceImpl
import services.demographics.Impl.cockroachdb.RaceServiceImpl
import services.demographics.{RaceService, RoleService}
import services.documents.DocumentService
import services.documents.Impl.cockroachdb.DocumentServiceImpl
import services.login.LoginService
import services.subjects.Impl.cockroachdb.MatricSubjectsServiceImpl
import services.subjects.MatricSubjectsService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future



class MatricSubjectsController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) {
  type DomainObject = MatricSubjects

  def className: String = "MatricSubjectsController"

  def domainService: MatricSubjectsService = MatricSubjectsService.roach

  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[MatricSubjects]] = for {
            results: Option[MatricSubjects] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[MatricSubjects]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[MatricSubjects]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[MatricSubjects] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[MatricSubjects]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }


  def getMatricSubjectsById(subjectCode: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(subjectCode)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllMatricSubjects: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteMatricSubjects: Action[JsValue] = Action.async(parse.json) {
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

package controllers.subjects



import controllers.ApiResponse
import domain.subjects.UniversityCourses
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
import services.subjects.Impl.cockroachdb.UniversityCoursesServiceImpl
import services.subjects.{MatricSubjectsService, UniversityCoursesService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future



class UniversityCoursesController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) {
  type DomainObject =UniversityCourses

  def className: String = "UniversityCoursesController"

  def domainService : UniversityCoursesService = UniversityCoursesService.roach

  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[UniversityCourses]] = for {
            results: Option[UniversityCourses] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[UniversityCourses]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[UniversityCourses]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[UniversityCourses] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[UniversityCourses]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }


  def getUniversityCoursesById(courseCode: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(courseCode)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllUniversityCourses: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteUniversityCourses: Action[JsValue] = Action.async(parse.json) {
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

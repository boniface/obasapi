package controllers.academics

import controllers.ApiResponse
import domain.academics.Course
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.{Logger, Logging}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.login.LoginService
import services.academics.CourseService
import util.HelperUtil

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class CourseController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {
  type DomainObject = Course
  def className: String = "CourseController"
  override val logger: Logger = Logger(className)
  def domainService: CourseService = CourseService.apply
  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Create request with body: " + entity)
      entity match {
        case Right(value) =>
          val copy = value.copy(id = HelperUtil.codeGen(value.courseName))
          logger.info("Saving course: " + copy)
          val response: Future[Option[DomainObject]] = for {
            results: Option[DomainObject] <- domainService.saveEntity(copy)
          } yield results
          api.requestResponse[Option[DomainObject]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Update request with body: " + entity)
      entity match {
        case Right(value) =>
          val response: Future[Option[DomainObject]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[DomainObject] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[DomainObject]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def read(courseId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by id: " + courseId)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(courseId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAll: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve all requested")
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def delete: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Delete request with body: " + entity)
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

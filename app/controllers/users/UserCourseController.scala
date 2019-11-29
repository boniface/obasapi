package controllers.users

import controllers.ApiResponse
import domain.users.UserCourse
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.libs.json.{JsValue, Json}
import play.api.{Logger, Logging}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.login.LoginService
import services.users.UserCourseService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserCourseController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {
  type DomainObject = UserCourse
  def className: String = "UserCourseController"
  override val logger: Logger = Logger(className)
  def domainService: UserCourseService = UserCourseService.apply
  def loginService: LoginService = LoginService.apply


  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Create request with body: " + entity)
      println("Create request with body: " + entity)
      entity match {
        case Right(value) =>
          val response: Future[Option[DomainObject]] = for {
            results: Option[DomainObject] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[DomainObject]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def read(userId: String, institutionId: String, courseId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by userId: " + userId + " and institutionId: " + institutionId + " courseId: " + courseId)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(userId, institutionId, courseId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getCoursesForUserPerInstitution(userId: String, institutionId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by userId: " + userId + " and institutionId: " + institutionId)
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntitiesForUserPerInstitution(userId, institutionId)
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def getCoursesForUser(userId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by userId: " + userId)
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntitiesForUser(userId)
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def getAll: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve all requested")
      println("Retrieve all requested")
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def delete: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Delete request with body: " + entity)
      println("Delete request with body: " + entity)
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

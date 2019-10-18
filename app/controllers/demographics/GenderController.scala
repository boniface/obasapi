package controllers.demographics



import controllers.ApiResponse
import domain.demographics.Gender
import io.circe.generic.auto._
import javax.inject.Inject
import play.api.{Logger, Logging}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services.demographics.GenderService
import services.login.LoginService
import util.HelperUtil

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future



class  GenderController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {
  type DomainObject = Gender

  def className: String = "GenderController"
  override val logger: Logger = Logger(className)

  def domainService: GenderService = GenderService.roach

  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val copy = value.copy(genderId = HelperUtil.codeGen(value.genderName))
          logger.info("Saving gender: " + copy)
          val response: Future[Option[Gender]] = for {
            results: Option[Gender] <- domainService.saveEntity(copy)
          } yield results
          api.requestResponse[Option[Gender]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[Gender]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[Gender] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[Gender]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def getGenderById(genderId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(genderId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllGender: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteGender: Action[JsValue] = Action.async(parse.json) {
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

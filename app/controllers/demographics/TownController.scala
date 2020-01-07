package controllers.demographics

import controllers.ApiResponse
import domain.demographics.Town
import io.circe.generic.auto._
import javax.inject.Inject
import play.api.{Logger, Logging}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services.demographics.TownService
import services.login.LoginService
import util.HelperUtil

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Deprecated
class TownController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {
  type DomainObject = Town

  def className: String = "TownController"
  override val logger: Logger = Logger(className)

  def domainService: TownService = TownService.roach
  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val copy = value.copy(townCode = HelperUtil.codeGen(value.townName))
          logger.info("Saving town: " + copy)
          val response: Future[Option[Town]] = for {
            results: Option[Town] <- domainService.saveEntity(copy)
          } yield results
          api.requestResponse[Option[Town]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }
  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[Town]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[Town] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[Town]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }



  def getTownById(townCode: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(townCode)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllTown: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteTown: Action[JsValue] = Action.async(parse.json) {
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
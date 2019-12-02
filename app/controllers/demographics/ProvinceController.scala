package controllers.demographics

import controllers.ApiResponse
import domain.demographics.Province
import io.circe.generic.auto._
import javax.inject.Inject
import play.api.{Logger, Logging}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services.demographics.ProvinceService
import services.login.LoginService
import util.HelperUtil

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ProvinceController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {
  type DomainObject = Province

  def className: String = "ProvinceController"
  override val logger: Logger = Logger(className)

  def domainService: ProvinceService = ProvinceService.roach
  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val copy = value.copy(provinceCode = HelperUtil.codeGen(value.provinceName))
          logger.info("Saving province: " + copy)
          val response: Future[Option[Province]] = for {
            results: Option[Province] <- domainService.saveEntity(copy)
          } yield results
          api.requestResponse[Option[Province]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[Province]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[Province] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[Province]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }



  def getProvinceById(provinceCode: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(provinceCode)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllProvince: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteProvince: Action[JsValue] = Action.async(parse.json) {
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
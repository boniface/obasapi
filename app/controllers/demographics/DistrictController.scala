package controllers.demographics

import controllers.ApiResponse
import domain.demographics.District
import io.circe.generic.auto._
import javax.inject.Inject
import play.api.{Logger, Logging}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services.demographics.DistrictService
import services.login.LoginService
import util.HelperUtil

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class DistrictController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging  {
  type DomainObject = District

  def className: String = "DistrictController"
  override val logger: Logger = Logger(className)

  def domainService: DistrictService = DistrictService.roach
  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val copy = value.copy(districtCode = HelperUtil.codeGen(value.districtName))
          logger.info("Saving district: " + copy)
          val response: Future[Option[District]] = for {
            results: Option[District] <- domainService.saveEntity(copy)
          } yield results
          api.requestResponse[Option[District]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Option[District]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[District] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[District]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }



  def getDistrictById(districtCode: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(districtCode)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllDistrict: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteDistrict: Action[JsValue] = Action.async(parse.json) {
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

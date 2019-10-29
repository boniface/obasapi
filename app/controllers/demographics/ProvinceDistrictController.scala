package controllers.demographics

import controllers.ApiResponse
import domain.demographics.ProvinceDistrict
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.libs.json.{JsValue, Json}
import play.api.{Logger, Logging}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.demographics.ProvinceDistrictService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ProvinceDistrictController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {
  type DomainObject = ProvinceDistrict

  def className: String = "ProvinceDistrictController"
  override val logger: Logger = Logger(className)
  def domainService: ProvinceDistrictService = ProvinceDistrictService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          logger.info("Saving province district: " + value)
          val response: Future[Option[DomainObject]] = for {
            results: Option[DomainObject] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[DomainObject]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def getDistrictsInProvince(provinceCode: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by provinceCode: " + provinceCode)
      println("Retrieve by provinceCode: " + provinceCode)
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntitiesForProvince(provinceCode)
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def getProvinceForDistrict(districtCode: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve province by districtCode: " + districtCode)
      println("Retrieve province by districtCode: " + districtCode)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results.filter(value => value.districtCode == districtCode).headOption
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def read(provinceCode: String, districtCode: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by provinceCode: " + provinceCode + " and districtCode: " + districtCode)
      println("Retrieve by provinceCode: " + provinceCode + " and districtCode: " + districtCode)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(provinceCode, districtCode)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
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

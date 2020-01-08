package controllers.demographics

import controllers.ApiResponse
import domain.demographics.DistrictTown
import javax.swing.Action
import services.demographics.DistrictTownService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Deprecated
class DistrictTownController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {

  type DomainObject = DistrictTown
  def className: String = "DistrictTownController"
  override val logger: Logger = Logger(className)
  def domainService: DistrictTownService = DistrictTownService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          logger.info("Saving district town: " + value)
          val response: Future[Option[DomainObject]] = for {
            results: Option[DomainObject] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[DomainObject]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def getTownsInDistrict(districtCode: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by districtCode: " + districtCode)
      println("Retrieve by districtCode: " + districtCode)
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntitiesForDistrict(districtCode)
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def getDistrictForTown(townCode: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve district by townCode: " + townCode)
      println("Retrieve district by townCode: " + townCode)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results.filter(value => value.townCode == townCode).headOption
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def read(districtCode: String, townCode: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by districtCode: " + districtCode + " and townCode: " + townCode)
      println("Retrieve by districtCode: " + districtCode + " and townCode: " + townCode)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(districtCode, townCode)
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

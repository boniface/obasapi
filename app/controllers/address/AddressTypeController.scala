package controllers.address

import controllers.ApiResponse
import domain.address.AddressType
import io.circe.generic.auto._
import javax.inject.Inject
import play.api.{Logger, Logging}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services.address.AddressTypeService
import services.login.LoginService
import util.HelperUtil

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future



class AddressTypeController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {
  type DomainObject = AddressType

  def className: String = "AddressTypeController"
  override val logger: Logger = Logger(className)

  def domainService: AddressTypeService = AddressTypeService.roach

  def loginService: LoginService = LoginService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Create request with body: " + entity)
      entity match {
        case Right(value) =>
          val copy = value.copy(addressTypeID = HelperUtil.codeGen(value.addressName))
          logger.info("Saving address type: " + copy)
          val response: Future[Option[AddressType]] = for {
            results: Option[AddressType] <- domainService.saveEntity(copy)
          } yield results
          api.requestResponse[Option[AddressType]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Update request with body: " + entity)
      entity match {
        case Right(value) =>
          val response: Future[Option[AddressType]] = for {
//            _ <- loginService.checkLoginStatus(request)
            results: Option[AddressType] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[AddressType]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def getAddressTypeById(addressTypeID: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by id: " + addressTypeID)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(addressTypeID)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllAddressType: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve all requested")
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteAddressType: Action[JsValue] = Action.async(parse.json) {
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

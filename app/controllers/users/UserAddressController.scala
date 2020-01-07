package controllers.users

import controllers.ApiResponse
import domain.users.UserAddress
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.{Logger, Logging}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.login.LoginService
import services.users.UserAddressService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserAddressController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging {

  type DomainObject = UserAddress

  def className: String = "UserAddressController"
  override val logger: Logger = Logger(className)
  def domainService: UserAddressService = UserAddressService.apply
  def loginService: LoginService = LoginService.apply


  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Create request with body: " + entity)
      println("Create request with body: " + entity)
      entity match {
        case Right(value) =>
          val response: Future[Option[UserAddress]] = for {
            results: Option[UserAddress] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[UserAddress]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def update: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      logger.info("Update request with body: " + entity)
      println("Update request with body: " + entity)
      entity match {
        case Right(value) =>
          val response: Future[Option[UserAddress]] = for {
            _ <- loginService.checkLoginStatus(request)
            results: Option[UserAddress] <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Option[UserAddress]](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }

  def getUserAddresses(userId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by userId: " + userId)
      println("Retrieve by userId: " + userId)
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntityForUser(userId)
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def getUserAddress(userId: String, addressTypeId: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve by userId: " + userId + " and addressTypeId: " + addressTypeId)
      println("Retrieve by userId: " + userId + " and addressTypeId: " + addressTypeId)
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(userId, addressTypeId)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllUserAddress: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("Retrieve all requested")
      println("Retrieve all requested")
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteUserAddress: Action[JsValue] = Action.async(parse.json) {
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

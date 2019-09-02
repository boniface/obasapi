package controllers.setup.security

import java.time.LocalDateTime

import io.circe.generic.auto._
import controllers.ApiResponse
import domain.security.ApiKeys
import javax.inject.Inject
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.security.ApiKeysService
import util.APPKeys

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Deprecated
class ApiKeysController @Inject()
( api: ApiResponse,cc: ControllerComponents) extends AbstractController(cc)  {

  type DomainObject = ApiKeys

  def className: String = "ApiKeysController"

  def service = ApiKeysService.apply

  //implemented for a once-off run
  def create(phrase: String): Action[AnyContent] = ???

}

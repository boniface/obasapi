package controllers.users

import controllers.ApiResponse
import domain.login.{ChangePassword, LoginToken}
import javax.inject.Inject
import play.api.{Logger, Logging}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, ControllerComponents, Request}
import services.login.LoginService
import services.users.UserChangePasswordService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserChangePasswordController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) with Logging  {

  def className: String = "UserChangePasswordController"
  override val logger: Logger = Logger(className)

  def loginService: LoginService = LoginService.apply
  def domainService: UserChangePasswordService = UserChangePasswordService.apply

}

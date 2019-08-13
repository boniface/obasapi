package services.login.Impl

import domain.login.{Login, Register}
import org.scalatest.FunSuite
import services.login.LoginService

import scala.concurrent.Await
import scala.concurrent.duration._

class LoginServiceImplTest extends FunSuite {

  val loginService = LoginService

  test("testCheckLoginStatus") {

  }

  test("testIsSecurityEnabled") {

  }

  test("testCheckFileSize") {

  }

  test("testForgotPassword") {

  }

  test("testIsUserRegistered") {

  }

  test("testResetPasswordRequest") {

  }

  test("testLogOut") {

  }

  test("testGetLoginToken") {
    val login = Login("cyfotyasi@gmail.com", "EVA6R5nF")
    val token = Await.result(loginService.apply.getLoginToken(login), 2.minutes)
    println(token)
    assert(token.isDefined)

  }

  test("testRegister") {
    val register: Register = Register("cyfotyasi@gmail.com")
    val isRegistered = Await.result(loginService.apply.register(register), 2.minutes)
    assert(isRegistered == true)
  }

}

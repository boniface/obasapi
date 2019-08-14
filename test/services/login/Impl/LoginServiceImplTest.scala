package services.login.Impl

import domain.login.{Login, Register, ResetKey}
import domain.security.ResetToken
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
    val register = Register("cyfotyasi@gmail.com")
    val result = Await.result(loginService.apply.forgotPassword(register), 2.minutes)
    println(result)
    assert(result.nonEmpty)

  }

  test("testIsUserRegistered") {

  }

  test("testResetPasswordRequest") {
    val resetKey = "f3ozxsunqaqmiwox3ru9b6mdznidxnq3"
    val reset = Await.result(loginService.apply.resetPasswordRequest(resetKey), 2.minutes)
    println(reset)
    assert(reset == true)


  }

  test("testLogOut") {

    val logout = Register("cyfotyasi@gmail.com")
    val isLoggedOut = Await.result(loginService.apply.logOut(logout), 2.minutes)
    println(isLoggedOut)
    assert(isLoggedOut == true)

  }

  test("testGetLoginToken") {
    val login = Login("cyfotyasi@gmail.com", "EVA6R5nF")
    val token = Await.result(loginService.apply.getLoginToken(login), 2.minutes)
    println(token)
    assert(token.isDefined)
  }

  test("testRegister") {
    val register: Register = Register("nombilasoso@gmail.com")
    val isRegistered = Await.result(loginService.apply.register(register), 2.minutes)
    assert(isRegistered == true)
  }

}

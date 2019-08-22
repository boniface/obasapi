package services.security.impl

import org.scalatest.FunSuite

class AuthenticationServiceImplTest extends FunSuite {

  val auth = new AuthenticationServiceImpl

  test("testGenerateRandomPassword") {
    val result = auth.generateRandomPassword()
    println(result)
  }

  test("testCheckPassword") {

  }

  test("testGetHashedPassword") {
    val password = auth.generateRandomPassword()
    val result = auth.getHashedPassword(password)
    println(password, result)

  }

}

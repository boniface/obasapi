package controllers.login

import domain.login.{Login, Register}
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest,Injecting}


class LoginControllerTest extends PlaySpec with GuiceOneAppPerTest with Injecting {

  val entity = Login("cyfotyasi@gmail.com", "12344321")
  val token = "eyJraWQiOiJQVUJMSUNLRVkiLCJhbGciOiJFUzI1NiJ9.eyJpc3MiOiJIQVNIQ09ERS5aTSIsImF1ZCI6IlNJVEVVU0VSUyIsImV4cCI6MTU0NTA2NzM0OSwianRpIjoieXhjRG9EVGh1bHlQNTJLcnVzVm5TZyIsImlhdCI6MTU0NDk4MDk0OSwibmJmIjoxNTQ0OTgwODI5LCJzdWIiOiJTaXRlIEFjY2VzcyIsImVtYWlsIjoiYm9uaWZhY2VAa2FiYXNvLmNvbSIsInJvbGUiOiJTSVRFX0FETUlOIiwic2l0ZUlkIjoiNWZkZDE4ZWVmNmE4OGVjMDNiNDQxMjhiMWZiMTkwMDUiLCJVU0VSSUQiOiIzMTE5NWU4MDFlMjc2MTFjNzE5NzczZjU1MTk5YjVmMiIsImFnZW50IjoiJDJhJDEyJGFXbzhFblNmSUUyUXdVbUk2NDFGYWVVeXQ3YWszQ25qRlRhT1A0ekcvbmp6dlhDUWdva04uIn0.uGRoC1uFBU8cfNAZgATWITyqfvBrEaqVHjW_4ymGe5KGwLIqlE2UfDAAVTNqUUQDUE-5K8PlV4j2RKydMhcZEg"

  "EntityController " should {

    "Register" in {
      val register: Register = Register("test@gmail.com")
      val url =  "/login/register"
      val request = route(app, FakeRequest(POST, url)
        .withJsonBody(Json.toJson(register))
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))

    }

      "Login " in {
        val login = Login("test@gmail.com", "Y1O0EnCa")
        val request = route(app, FakeRequest(POST, "/login/login")
          .withJsonBody(Json.toJson(login))
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is: ", contentAsString(request))
      }

    "ForgotPassword" in {
      val register: Register = Register("test@gmail.com")
      val request = route(app, FakeRequest(POST, "/login/forgotpassword")
        .withJsonBody(Json.toJson(register))
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))
    }

    "ResetPassword" in {
      val resetKey = "7ptfihi0cotz2slxy3vg3xhoo3amikge"
      val request = route(app, FakeRequest(GET, "/login/passwordreset/" + resetKey)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))
    }

    "Logout" in {
      val register: Register = Register("test@gmail.com")
      val request = route(app, FakeRequest(GET, "/login/logout/")
        .withJsonBody(Json.toJson(register))
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))
    }

    "IsUserRegistered" in {
      val register: Register = Register("test@gmail.com")
      val request = route(app, FakeRequest(GET, "/login/isregistered/")
        .withJsonBody(Json.toJson(register))
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))
    }



//    "Get Accounts  " in {
//      val request = route(app, FakeRequest(GET, "/login/user/" + "boniface@kabaso.com")
//        .withHeaders(AUTHORIZATION -> token)
//      ).get
//      status(request) mustBe OK
//      contentType(request) mustBe Some("application/json")
//      println(" The Content is: ", contentAsString(request))
//
//
//    }
//
//    "Read Entities" in {
//      val request = route(app, FakeRequest(GET, "/login/getall")
//        .withHeaders(AUTHORIZATION -> token)
//      ).get
//      status(request) mustBe OK
//      contentType(request) mustBe Some("application/json")
//      println(" The Content is: ", contentAsString(request))
//
//
//    }
//
//    "Update Entity" in {
//      val updatedEntity = entity.copy(email = "Updated")
//      val request = route(app, FakeRequest(POST, "/login/update")
//        .withJsonBody(Json.toJson(updatedEntity))
//        .withHeaders(AUTHORIZATION -> token)
//      ).get
//      status(request) mustBe OK
//      contentType(request) mustBe Some("application/json")
//      println("The Content is: ", contentAsString(request))
//    }
//
//    "Delete Entities " in {
//      val request = route(app, FakeRequest(POST, "/login/delete" )
//        .withJsonBody(Json.toJson(entity))
//        .withHeaders(AUTHORIZATION -> token)
//      ).get
//      status(request) mustBe OK
//      contentType(request) mustBe Some("application/json")
//      println(" The Content is: ", contentAsString(request))
//
//    }
  }

}

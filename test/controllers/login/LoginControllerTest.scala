package controllers.login

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Injecting}

class LoginControllerTest extends PlaySpec with GuiceOneAppPerTest with Injecting {
//  val entity = Role("1", "TEST", "This is a Test Role")
//  val token = "eyJraWQiOiJQVUJMSUNLRVkiLCJhbGciOiJFUzI1NiJ9.eyJpc3MiOiJIQVNIQ09ERS5aTSIsImF1ZCI6IlNJVEVVU0VSUyIsImV4cCI6MTU0NTA2NzM0OSwianRpIjoieXhjRG9EVGh1bHlQNTJLcnVzVm5TZyIsImlhdCI6MTU0NDk4MDk0OSwibmJmIjoxNTQ0OTgwODI5LCJzdWIiOiJTaXRlIEFjY2VzcyIsImVtYWlsIjoiYm9uaWZhY2VAa2FiYXNvLmNvbSIsInJvbGUiOiJTSVRFX0FETUlOIiwic2l0ZUlkIjoiNWZkZDE4ZWVmNmE4OGVjMDNiNDQxMjhiMWZiMTkwMDUiLCJVU0VSSUQiOiIzMTE5NWU4MDFlMjc2MTFjNzE5NzczZjU1MTk5YjVmMiIsImFnZW50IjoiJDJhJDEyJGFXbzhFblNmSUUyUXdVbUk2NDFGYWVVeXQ3YWszQ25qRlRhT1A0ekcvbmp6dlhDUWdva04uIn0.uGRoC1uFBU8cfNAZgATWITyqfvBrEaqVHjW_4ymGe5KGwLIqlE2UfDAAVTNqUUQDUE-5K8PlV4j2RKydMhcZEg"
//
//  "EntityController " should {
//
//
//
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
//    "Login " in {
//      val registration = LoginCredential("boniface@kabaso.com",
//        "5fdd18eef6a88ec03b44128b1fb19005","31195e801e27611c719773f55199b5f2","sFN0cSIh")
//      val request = route(app, FakeRequest(POST, "/login/getlogin")
//        .withJsonBody(Json.toJson(registration))
//        .withHeaders(AUTHORIZATION -> token)
//      ).get
//      status(request) mustBe OK
//      contentType(request) mustBe Some("application/json")
//      println(" The Content is: ", contentAsString(request))
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
//      val updatedEntity = entity.copy(roleName = "Updated")
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
//  }

}

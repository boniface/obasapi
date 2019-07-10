package controllers.mail

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Injecting}

class MailConfigControllerTest extends PlaySpec with GuiceOneAppPerTest with Injecting {
  // val entity = Role("1", "TEST", "This is a Test Role")
  // val token = "eyJsDbNTlcQag"


//  "EntityController " should {
//
//    "Create Entity " in {
//      val request = route(app, FakeRequest(POST, "/roles/userSubscriptionUpdate")
//        .withJsonBody(Json.toJson(entity))
//        .withHeaders(AUTHORIZATION -> token)
//      ).get
//      status(request) mustBe OK
//      contentType(request) mustBe Some("application/json")
//      println(" The Content is: ", contentAsString(request))
//
//    }
//
//    "Read Entity " in {
//      val request = route(app, FakeRequest(GET, "/roles/get/" + entity.id)
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
//      val request = route(app, FakeRequest(GET, "/roles/getall")
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
//      val request = route(app, FakeRequest(POST, "/roles/update")
//        .withJsonBody(Json.toJson(updatedEntity))
//        .withHeaders(AUTHORIZATION -> token)
//      ).get
//      status(request) mustBe OK
//      contentType(request) mustBe Some("application/json")
//      println("The Content is: ", contentAsString(request))
//    }
//
//    "Delete Entities " in {
//      val request = route(app, FakeRequest(POST, "/roles/delete" )
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

package controllers.mail

import domain.mail.SmtpConfig
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Injecting}

class SmtpConfigControllerTest  extends PlaySpec with GuiceOneAppPerTest with Injecting {
  val entity = SmtpConfig("1", 587, "smtp@gmail.com","username","password")
  val token = "eyJsDbNTlcQag"


  "EntityController " should {

    "Create Entity " in {
      val request = route(app, FakeRequest(POST, "/smtp/create")
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))

    }

    "Read Entity " in {
      val request = route(app, FakeRequest(GET, "/smtp/get/" + entity.id)
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))


    }

    "Get Entities" in {
      val request = route(app, FakeRequest(GET, "/smtp/all")
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))


    }

    "Update Entity" in {
      val updatedEntity = entity.copy(username = "Updated")
      val request = route(app, FakeRequest(POST, "/roles/update")
        .withJsonBody(Json.toJson(updatedEntity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }

    "Delete Entities " in {
      val request = route(app, FakeRequest(POST, "/roles/delete" )
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))

    }
  }

}

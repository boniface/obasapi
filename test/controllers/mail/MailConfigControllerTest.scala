package controllers.mail

import java.time.LocalDateTime

import domain.mail.{MailConfig, SmtpConfig}
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Injecting}

class MailConfigControllerTest extends PlaySpec with GuiceOneAppPerTest with Injecting {
  val entity = MailConfig("1", "1", "11", "eeee", "host", "908", "active", LocalDateTime.now)
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
      val request = route(app, FakeRequest(GET, "get//$id" + entity.id)
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))


    }

    "Read Entities" in {
      val request = route(app, FakeRequest(GET, "/all")
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))


    }

    "Update Entity" in {
      val updatedEntity = entity.copy(siteId = "Updated")
      val request = route(app, FakeRequest(POST, "/update")
        .withJsonBody(Json.toJson(updatedEntity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }

    "Delete Entities " in {
      val request = route(app, FakeRequest(POST, "/delete" )
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))

    }
  }

}

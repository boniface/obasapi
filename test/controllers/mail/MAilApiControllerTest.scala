package controllers.mail

import domain.mail.MailApi
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Injecting}

class MAilApiControllerTest extends PlaySpec with GuiceOneAppPerTest with Injecting {
  val entity = MailApi(HashcodeKeys.MAILKEY, "KEY", "SENDER")
  val token = "eyJsDbNTlcQag"


  "EntityController " should {

    "Create Entity " in {
      val request = route(app, FakeRequest(POST, "/mail/api/userSubscriptionUpdate")
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))

    }

    "Read Entity " in {
      val request = route(app, FakeRequest(GET, "/mail/api/get/" + entity.id)
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))


    }

    "Read Entities" in {
      val request = route(app, FakeRequest(GET, "/mail/api/getall")
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))


    }

    "Update Entity" in {
      val updatedEntity = entity.copy(sender = "Updated")
      val request = route(app, FakeRequest(POST, "/mail/api/update")
        .withJsonBody(Json.toJson(updatedEntity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }

    "Delete Entities " in {
      val request = route(app, FakeRequest(POST, "/mail/api/delete" )
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))

    }
  }

}

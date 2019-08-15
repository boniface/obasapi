package controllers.mail

import domain.mail.MailApi
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Injecting}
import util.APPKeys

class MailTest  extends PlaySpec with GuiceOneAppPerTest with Injecting {
  val entity = MailApi(APPKeys.SENDGRID_ID, "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", "do_not_reply@hashcode.zm")
  val token = "eyJsDbNTlcQag"


  "EntityController " should {

    "Create Entity " in {
      val request = route(app, FakeRequest(POST, "/mail/api/create")
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

    "Get Entities" in {
      val request = route(app, FakeRequest(GET, "/mail/api/all")
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))


    }

  }

}

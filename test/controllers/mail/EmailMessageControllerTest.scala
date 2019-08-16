package controllers.mail

import domain.mail.{EmailMessage}
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Injecting}

class EmailMessageControllerTest extends PlaySpec with GuiceOneAppPerTest with Injecting {
  val entity = EmailMessage("wieseaj@gmail.com", "PassWord reset", "pls change it")
  val token = "eyJsDbNTlcQag"


  "EntityController " should {

    "Create Entity " in {

      val request = route(app, FakeRequest(POST, "/mail/message/create")

        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))

    }


    "Read Entity " in {
      val request = route(app, FakeRequest(GET, "/mail/message/$email" + entity.email)

        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))


    }

    "Get Entities" in {


      val request = route(app, FakeRequest(GET, "/mail/message/all")
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))


    }

    "Update Entity" in {

      val updatedEntity = entity.copy(email = "Updated")
      val request = route(app, FakeRequest(POST, "/mail/message/update")
        .withJsonBody(Json.toJson(updatedEntity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }

    "Delete Entities " in {

      val request = route(app, FakeRequest(POST, "/mail/message/delete" )

        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is: ", contentAsString(request))

    }

  }


}

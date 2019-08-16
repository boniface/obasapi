package controllers.users

import domain.users.UserCommunication
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest,Injecting}

class UserCommunicationControllerTest extends PlaySpec with GuiceOneAppPerTest  with Injecting {

  val entity =UserCommunication("1","IsiXhosa")
  val token ="eyJsDbNTlcQag"



  "EntityController" should {

    "Create Entity" in {

      val request = route(app, FakeRequest(POST, "/users/communication/create")
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }


    "Read Entity " in {

      val request = route(app, FakeRequest(GET, "/users/communication/get/$communicationId" + entity.communicationId)
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }

    "Get Entities" in {
      val request = route(app, FakeRequest(GET, "/users/communication/all")
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))

    }

    "Update Entity" in {
      val updatedEntity = entity.copy(description = "updated")
      val request = route(app, FakeRequest(POST, "/users/communication/update")
        .withJsonBody(Json.toJson(updatedEntity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }

    "Delete Entities" in {
      val request = route(app, FakeRequest(POST, "/users/communication/delete")
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))

    }

  }
}

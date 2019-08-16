package controllers.users


import domain.users.UserContacts
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest,Injecting}

class UserContactControllerTest extends PlaySpec with GuiceOneAppPerTest  with Injecting {

  val entity =UserContacts("1","095 345 8976","084 367 4763","testEmail@usercont.com")
  val token ="eyJsDbNTlcQag"



  "EntityController" should {

    "Create Entity" in {

      val request = route(app, FakeRequest(POST, "/users/contacts/create")
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }


    "Read Entity " in {

      val request = route(app, FakeRequest(GET, "/users/contacts/get/$userContactId" + entity.userContactId)
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }

    "Get Entities" in {
      val request = route(app, FakeRequest(GET, "/users/contacts/all")
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))

    }

    "Update Entity" in {
      val updatedEntity = entity.copy(cellNumber = "updated")
      val request = route(app, FakeRequest(POST, "/users/contacts/update")
        .withJsonBody(Json.toJson(updatedEntity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }

    "Delete Entities" in {
      val request = route(app, FakeRequest(POST, "/users/contacts/delete")
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))

    }

  }
}

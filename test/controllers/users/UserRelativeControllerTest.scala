package controllers.users

import domain.users.UserRelative
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest,Injecting}

class UserRelativeControllerTest extends PlaySpec with GuiceOneAppPerTest  with Injecting {

  val entity =UserRelative("1","089 765 4755","Luwis","Father","parant@gmail.com")
  val token ="eyJsDbNTlcQag"



  "EntityController" should{

    "Create Entity" in{

      val request =route(app, FakeRequest(POST,"/relative/create")
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION-> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }
  }

  "Read Entity " in{

    val request = route(app, FakeRequest(GET,"/relative/get" +entity.userRelativeId)
      .withHeaders(AUTHORIZATION -> token)
    ).get
    status(request) mustBe OK
    contentType(request)mustBe Some("application/Json")
    println("The Content is: ", contentAsString(request))
  }

  "Get Entities" in{
    val request =route(app, FakeRequest(GET, "/relative/all")
      .withHeaders(AUTHORIZATION -> token)
    ).get
    status(request) mustBe OK
    contentType(request) mustBe Some("application/json")
    println("The Content is: ", contentAsString(request))

  }

  "Update Entity" in{
    val updatedEntity =entity.copy(name ="updated")
    val request =route(app, FakeRequest(POST, "/relative/update")
      .withJsonBody(Json.toJson(updatedEntity))
      .withHeaders(AUTHORIZATION -> token)
    ).get
    status(request) mustBe OK
    contentType(request) mustBe Some("application/json")
    println("The Content is: ", contentAsString(request))
  }

  "Delete Entities" in {
    val request =route(app,FakeRequest(POST,"/relative/delete")
      .withJsonBody(Json.toJson(entity))
      .withHeaders(AUTHORIZATION ->token)
    ).get
    status(request) mustBe OK
    contentType(request) mustBe Some("application/json")
    println("The Content is: ", contentAsString(request))

  }


}

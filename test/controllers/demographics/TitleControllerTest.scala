package controllers.demographics


import domain.demographics.Title
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest,Injecting}

class TitleControllerTest extends PlaySpec with GuiceOneAppPerTest  with Injecting {

  val entity =Title("1","Mr")
  val token ="exkfJdDbnT1cQa"



  "EntityController" should{

    "Create Entity" in{

      val request =route(app, FakeRequest(POST,"/title/create")
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION-> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }
  }

  "Read Entity " in{

    val request = route(app, FakeRequest(GET,"/title/get" +entity.titleId)
      .withHeaders(AUTHORIZATION -> token)
    ).get
    status(request) mustBe OK
    contentType(request)mustBe Some("application/Json")
    println("The Content is: ", contentAsString(request))
  }

  "Get Entities" in{
    val request =route(app, FakeRequest(GET, "/title/all")
      .withHeaders(AUTHORIZATION -> token)
    ).get
    status(request) mustBe OK
    contentType(request) mustBe Some("application/json")
    println("The Content is: ", contentAsString(request))

  }

  "Update Entity" in{
    val updatedEntity =entity.copy(titlename ="updated")
    val request =route(app, FakeRequest(POST, "/title/update")
      .withJsonBody(Json.toJson(updatedEntity))
      .withHeaders(AUTHORIZATION -> token)
    ).get
    status(request) mustBe OK
    contentType(request) mustBe Some("application/json")
    println("The Content is: ", contentAsString(request))
  }

  "Delete Entities" in {
    val request =route(app,FakeRequest(POST,"/title/delete")
      .withJsonBody(Json.toJson(entity))
      .withHeaders(AUTHORIZATION ->token)
    ).get
    status(request) mustBe OK
    contentType(request) mustBe Some("application/json")
    println("The Content is: ", contentAsString(request))

  }


}

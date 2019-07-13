package controllers.institutions

import domain.institutions.School
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest,Injecting}

class SchoolControllerTest extends PlaySpec with GuiceOneAppPerTest  with Injecting {

  val entity =School("1","Qhayiya HighSchool","Hermanus Zwelihle 7200","Western Cape")
  val token ="eyJsDbNTlcQag"



  "EntityController" should{

    "Create Entity" in{

      val request =route(app, FakeRequest(POST,"/school/create")
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION-> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }
  }

  "Read Entity " in{

    val request = route(app, FakeRequest(GET,"/school/get" +entity.schoolId)
      .withHeaders(AUTHORIZATION -> token)
    ).get
    status(request) mustBe OK
    contentType(request)mustBe Some("application/Json")
    println("The Content is: ", contentAsString(request))
  }

  "Get Entities" in{
    val request =route(app, FakeRequest(GET, "/school/all")
      .withHeaders(AUTHORIZATION -> token)
    ).get
    status(request) mustBe OK
    contentType(request) mustBe Some("application/json")
    println("The Content is: ", contentAsString(request))

  }

  "Update Entity" in{
    val updatedEntity =entity.copy(schoolName ="updated")
    val request =route(app, FakeRequest(POST, "/school/update")
      .withJsonBody(Json.toJson(updatedEntity))
      .withHeaders(AUTHORIZATION -> token)
    ).get
    status(request) mustBe OK
    contentType(request) mustBe Some("application/json")
    println("The Content is: ", contentAsString(request))
  }

  "Delete Entities" in {
    val request =route(app,FakeRequest(POST,"/school/delete")
      .withJsonBody(Json.toJson(entity))
      .withHeaders(AUTHORIZATION ->token)
    ).get
    status(request) mustBe OK
    contentType(request) mustBe Some("application/json")
    println("The Content is: ", contentAsString(request))

  }


}

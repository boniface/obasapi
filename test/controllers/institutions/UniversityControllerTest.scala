package controllers.institutions

import domain.institutions.University
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest,Injecting}

class UniversityControllerTest extends PlaySpec with GuiceOneAppPerTest  with Injecting {

  val entity =University("1","Cape Peninsula University of Technology"," Strand Cape Town","Western Cape")
  val token ="eyJsDbNTlcQag"



  "EntityController" should{

    "Create Entity" in{

      val request =route(app, FakeRequest(POST,"/university/create")
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION-> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }
  }

  "Read Entity " in{

    val request = route(app, FakeRequest(GET,"/university/get" +entity.universityId)
      .withHeaders(AUTHORIZATION -> token)
    ).get
    status(request) mustBe OK
    contentType(request)mustBe Some("application/Json")
    println("The Content is: ", contentAsString(request))
  }

  "Get Entities" in{
    val request =route(app, FakeRequest(GET, "/university/all")
      .withHeaders(AUTHORIZATION -> token)
    ).get
    status(request) mustBe OK
    contentType(request) mustBe Some("application/json")
    println("The Content is: ", contentAsString(request))

  }

  "Update Entity" in{
    val updatedEntity =entity.copy(universityName ="updated")
    val request =route(app, FakeRequest(POST, "/university/update")
      .withJsonBody(Json.toJson(updatedEntity))
      .withHeaders(AUTHORIZATION -> token)
    ).get
    status(request) mustBe OK
    contentType(request) mustBe Some("application/json")
    println("The Content is: ", contentAsString(request))
  }

  "Delete Entities" in {
    val request =route(app,FakeRequest(POST,"/university/delete")
      .withJsonBody(Json.toJson(entity))
      .withHeaders(AUTHORIZATION ->token)
    ).get
    status(request) mustBe OK
    contentType(request) mustBe Some("application/json")
    println("The Content is: ", contentAsString(request))

  }


}

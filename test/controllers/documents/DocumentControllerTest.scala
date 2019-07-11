package controllers.documents

import java.time.LocalDateTime

import domain.documents.Document
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Injecting}

class DocumentControllerTest extends PlaySpec with GuiceOneAppPerTest  with Injecting {

  val entity =Document("cyfotyasi@gmail.com","Malusi","htpp://www.google.com/drive","Year 2017","National Certification","N",LocalDateTime.now(),"yes")
  val token ="exkfJdDbnT1cQa"



  "EntityController" should{

    "Create Entity" in{

      val request =route(app, FakeRequest(POST,"/address/create")
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION-> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }
  }

  "Read Entity " in{

    val request = route(app, FakeRequest(GET,"/doc/get" +entity.email)
      .withHeaders(AUTHORIZATION -> token)
    ).get
    status(request) mustBe OK
    contentType(request)mustBe Some("application/Json")
    println("The Content is: ", contentAsString(request))
  }

  "Get Entities" in{
    val request =route(app, FakeRequest(GET, "/doc/all")
      .withHeaders(AUTHORIZATION -> token)
    ).get
    status(request) mustBe OK
    contentType(request) mustBe Some("doc/json")
    println("The Content is: ", contentAsString(request))

  }

  "Update Entity" in{
    val updatedEntity =entity.copy(description ="updated")
    val request =route(app, FakeRequest(POST, "/doc/update")
      .withJsonBody(Json.toJson(updatedEntity))
      .withHeaders(AUTHORIZATION -> token)
    ).get
    status(request) mustBe OK
    contentType(request) mustBe Some("application/json")
    println("The Content is: ", contentAsString(request))
  }

  "Delete Entities" in {
    val request =route(app,FakeRequest(POST,"/doc/delete")
      .withJsonBody(Json.toJson(entity))
      .withHeaders(AUTHORIZATION ->token)
    ).get
    status(request) mustBe OK
    contentType(request) mustBe Some("application/json")
    println("The Content is: ", contentAsString(request))

  }


}

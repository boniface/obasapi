package controllers.demographics

import domain.demographics.DistrictTown
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Injecting}

class Test extends PlaySpec with GuiceOneAppPerTest  with Injecting  {

  val entity = DistrictTown("CCAI-4HTCX","DDOS-2WJUX")

  "DistrictTownController" should {
    "Create Entity" in {

      val request = route(app, FakeRequest(POST, "/demographics/districttown/create")
        .withJsonBody(Json.toJson(entity))
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }
  }

}

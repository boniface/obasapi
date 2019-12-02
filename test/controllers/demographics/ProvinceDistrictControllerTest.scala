package controllers.demographics

import domain.demographics.ProvinceDistrict
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Injecting}

class ProvinceDistrictControllerTest extends PlaySpec with GuiceOneAppPerTest  with Injecting   {

  val entity = ProvinceDistrict("WWET-DOUTX","CCTO-FVLTZ")

  "ProvinceDistrictController" should {

    "Create Entity" in {

      val request = route(app, FakeRequest(POST, "/demographics/provincedistrict/create")
        .withJsonBody(Json.toJson(entity))
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }

  }

}

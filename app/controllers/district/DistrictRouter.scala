package controllers.district

import javax.inject.Inject

import play.api.routing.sird._

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter

class DistrictRouter @Inject()(districtController: DistrictController
                               , provinceController: ProvinceController
                               , townController: TownController)
  extends SimpleRouter {
  override def routes: Routes = {


    //DISTRICT
    case GET(p"/district/all") =>
      districtController.getAllDistrict
    case GET(p"/district/get/$districtCode") =>
      districtController.getDistrictById(districtCode)
    case POST(p"/district/create") =>
      districtController.create
    case POST(p"/district/update") =>
      districtController.update
    case POST(p"/district/delete") =>
      districtController.deleteDistrict


    //PROVINCE
    case GET(p"/province/all") =>
      provinceController.getAllProvince
    case GET(p"/province/get/$provinceCode") =>
      provinceController.getProvinceById(provinceCode)
    case POST(p"/province/create") =>
      provinceController.create
    case POST(p"/province/update") =>
      provinceController.update
    case POST(p"/province/delete") =>
      provinceController.deleteProvince

    //TOWN
    case GET(p"/town/all") =>
      townController.getAllTown
    case GET(p"/town/get/$townCode") =>
      townController.getTownById(townCode)
    case POST(p"/town/create") =>
      townController.create
    case POST(p"/town/update") =>
      townController.update
    case POST(p"/town/delete") =>
      townController.deleteTown
  }



}

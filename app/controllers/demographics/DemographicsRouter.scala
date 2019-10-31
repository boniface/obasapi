package controllers.demographics

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class DemographicsRouter @Inject()
(genderController: GenderController, raceController: RaceController, rolesController: RolesController,
 titleController: TitleController, districtController: DistrictController, provinceController: ProvinceController,
 townController: TownController, provinceDistrictController: ProvinceDistrictController, districtTownController: DistrictTownController
) extends SimpleRouter {
  override def routes: Routes = {

    //GENDER
    case GET(p"/gender/all") =>
      genderController.getAllGender
    case GET(p"/gender/get/$genderId") =>
      genderController.getGenderById(genderId)
    case POST(p"/gender/create") =>
      genderController.create
    case POST(p"/gender/update") =>
      genderController.update
    case POST(p"/gender/delete") =>
      genderController.deleteGender

    //RACE
    case GET(p"/race/all") =>
      raceController.getAllRace
    case GET(p"/race/get/$raceId") =>
      raceController.getRaceById(raceId)
    case POST(p"/race/create") =>
      raceController.create
    case POST(p"/race/update") =>
      raceController.update
    case POST(p"/race/delete") =>
      raceController.deleteRace

    //ROLES
    case GET(p"/roles/all") =>
      rolesController.getAllRoles
    case GET(p"/roles/get/$id") =>
      rolesController.getRoleById(id)
    case POST(p"/roles/create") =>
      rolesController.create
    case POST(p"/roles/update") =>
      rolesController.update
    case POST(p"/roles/delete") =>
      rolesController.deleteRole

    //TITLE
    case GET(p"/title/all") =>
      titleController.getAllTitle
    case GET(p"/title/get/$titleId") =>
      titleController.getTitleById(titleId)
    case POST(p"/title/create") =>
      titleController.create
    case POST(p"/title/update") =>
      titleController.update
    case POST(p"/title/delete") =>
      titleController.deleteTitle

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

    //PROVINCE_DISTRICT
    case GET(p"/provincedistrict/all") =>
      provinceDistrictController.getAll
    case GET(p"/provincedistrict/get/$provinceCode/$districtCode") =>
      provinceDistrictController.read(provinceCode, districtCode)
    case GET(p"/provincedistrict/getdistricts/$provinceCode") =>
      provinceDistrictController.getDistrictsInProvince(provinceCode)
    case GET(p"/provincedistrict/getdistrictprovince/$districtCode") =>
      provinceDistrictController.getProvinceForDistrict(districtCode)
    case POST(p"/provincedistrict/create") =>
      provinceDistrictController.create
    case POST(p"/provincedistrict/delete") =>
      provinceDistrictController.delete

    //DISTRICT_TOWN
    case GET(p"/districttown/all") =>
      districtTownController.getAll
    case GET(p"/districttown/get/$districtCode/$townCode") =>
      districtTownController.read(districtCode, townCode)
    case GET(p"/districttown/gettowns/$districtCode") =>
      districtTownController.getTownsInDistrict(districtCode)
    case GET(p"/districttown/gettowndistrict/$townCode") =>
      districtTownController.getDistrictForTown(townCode)
    case POST(p"/districttown/create") =>
      districtTownController.create
    case POST(p"/districttown/delete") =>
      districtTownController.delete

  }
}

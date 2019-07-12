package controllers.demographics

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class DemographicsRouter@Inject()
(genderController: GenderController,raceController: RaceController,
 rolesController: RolesController,titleController: TitleController) extends SimpleRouter{
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


  }


}

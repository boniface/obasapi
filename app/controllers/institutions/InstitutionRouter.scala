package controllers.institutions

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class InstitutionRouter @Inject()
(schoolController: SchoolController, universityController: UniversityController
)extends SimpleRouter {
  override def routes: Routes = {

    //SCHOOL
    case GET(p"/school/all") =>
      schoolController.getAllSchools
    case GET(p"/school/get/$schoolId") =>
      schoolController.getSchoolById(schoolId)
    case POST(p"/school/create") =>
      schoolController.create
    case POST(p"/school/update") =>
      schoolController.update
    case POST(p"/school/delete") =>
      schoolController.deleteSchool

    //UNIVERSITY
    case GET(p"/university/all") =>
      universityController.getAllUniversity
    case GET(p"/university/get/$universityId") =>
      universityController.getUniversityById(universityId)
    case POST(p"/university/create") =>
      universityController.create
    case POST(p"/university/update") =>
      universityController.update
    case POST(p"/university/delete") =>
      universityController.deleteUniversity


  }

}

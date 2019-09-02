package controllers.subjects

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class SubjectsRouter@Inject()
(matricSubjectsController: MatricSubjectsController,
 universityCoursesController: UniversityCoursesController)extends SimpleRouter{

  override def routes: Routes ={


    //MatricSubjects
    case GET(p"/matric/all") =>
      matricSubjectsController.getAllMatricSubjects
    case GET(p"/matric/get/$subjectCode") =>
      matricSubjectsController.getMatricSubjectsById(subjectCode)
    case POST(p"/matric/create") =>
      matricSubjectsController.create
    case POST(p"/matric/update") =>
      matricSubjectsController.update
    case POST(p"/matric/delete") =>
      matricSubjectsController.deleteMatricSubjects

    //University
    case GET(p"/university/all") =>
      universityCoursesController.getAllUniversityCourses
    case GET(p"/university/get/$courseCode") =>
      universityCoursesController.getUniversityCoursesById(courseCode)
    case POST(p"/university/create") =>
      universityCoursesController.create
    case POST(p"/university/update") =>
      universityCoursesController.update
    case POST(p"/university/delete") =>
      universityCoursesController.deleteUniversityCourses

  }


}















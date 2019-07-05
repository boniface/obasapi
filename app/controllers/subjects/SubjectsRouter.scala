package controllers.subjects

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._
import play.routing.Router

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
    case GET(p"/uni/all") =>
      universityCoursesController.getAllUniversityCourses
    case GET(p"/uni/get/$courseCode") =>
      universityCoursesController.getUniversityCoursesById(courseCode)
    case POST(p"/uni/create") =>
      universityCoursesController.create
    case POST(p"/uni/update") =>
      universityCoursesController.update
    case POST(p"/uni/delete") =>
      universityCoursesController.deleteUniversityCourses





  }


}















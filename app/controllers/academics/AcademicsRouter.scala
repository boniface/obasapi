package controllers.academics

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class AcademicsRouter @Inject()(
                                 courseController: CourseController,
                                 subjectController: SubjectController,
                                 courseSubjectController: CourseSubjectController
                               ) extends SimpleRouter {

  override def routes: Routes = {
    // Course
    case GET(p"/course/all") =>
      courseController.getAll
    case GET(p"/course/get/$courseId") =>
      courseController.read(courseId)
    case POST(p"/course/create") =>
      courseController.create
    case POST(p"/course/update") =>
      courseController.update
    case POST(p"/course/delete") =>
      courseController.delete

    // Subject
    case GET(p"/subject/all") =>
      subjectController.getAll
    case GET(p"/subject/get/$subjectId") =>
      subjectController.read(subjectId)
    case POST(p"/subject/create") =>
      subjectController.create
    case POST(p"/subject/update") =>
      subjectController.update
    case POST(p"/subject/delete") =>
      subjectController.delete

    // COURSE_SUBJECT
    case POST(p"/coursesubject/create") =>
      courseSubjectController.create
    case GET(p"/coursesubject/get/$courseId/$subjectId") =>
      courseSubjectController.read(courseId, subjectId)
    case GET(p"/coursesubject/all") =>
      courseSubjectController.getAll
    case GET(p"/coursesubject/getsubjects/$courseId") =>
      courseSubjectController.getCourseSubjects(courseId)
    case POST(p"/coursesubject/delete") =>
      courseSubjectController.delete
  }


}















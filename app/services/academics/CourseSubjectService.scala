package services.academics

import domain.academics.CourseSubject
import services.CrudService
import services.academics.Impl.cockroachdb.CourseSubjectServiceImpl

import scala.concurrent.Future

trait CourseSubjectService extends CrudService[CourseSubject] {

  def getEntity(courseId: String, subjectId: String): Future[Option[CourseSubject]]

  def getEntitiesForCourse(courseId: String): Future[Seq[CourseSubject]]

}

object CourseSubjectService {
  def apply: CourseSubjectService = new CourseSubjectServiceImpl()
}

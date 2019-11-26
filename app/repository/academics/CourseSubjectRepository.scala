package repository.academics

import domain.academics.CourseSubject
import repository.Repository
import repository.academics.impl.cockcroachdb.CourseSubjectRepositoryImpl

import scala.concurrent.Future

trait CourseSubjectRepository extends Repository[CourseSubject]{

  def getEntity(courseId: String, subjectId: String): Future[Option[CourseSubject]]

  def getEntitiesForCourse(courseId: String): Future[Seq[CourseSubject]]

}

object CourseSubjectRepository {
  def apply: CourseSubjectRepository = new CourseSubjectRepositoryImpl()
}

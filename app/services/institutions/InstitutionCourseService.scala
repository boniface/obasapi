package services.institutions

import domain.institutions.InstitutionCourse
import services.CrudService
import services.institutions.Impl.cockroachdb.InstitutionCourseServiceImpl

import scala.concurrent.Future

trait InstitutionCourseService extends CrudService[InstitutionCourse] {
  def getEntity(institutionId: String, courseId: String): Future[Option[InstitutionCourse]]
  def getEntitiesForInstitution(institutionId: String): Future[Seq[InstitutionCourse]]
}

object InstitutionCourseService {
  def apply: InstitutionCourseService = new InstitutionCourseServiceImpl()
}

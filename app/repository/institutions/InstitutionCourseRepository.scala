package repository.institutions

import domain.institutions.InstitutionCourse
import repository.Repository
import repository.institutions.impl.cockroachdb.InstitutionCourseRepositoryImpl

import scala.concurrent.Future

trait InstitutionCourseRepository extends Repository[InstitutionCourse] {
  def getEntity(institutionId: String, courseId: String): Future[Option[InstitutionCourse]]
  def getEntitiesForInstitution(institutionId: String): Future[Seq[InstitutionCourse]]
}

object InstitutionCourseRepository {
  def apply: InstitutionCourseRepository = new InstitutionCourseRepositoryImpl()
}

package services.institutions.Impl.cockroachdb

import domain.institutions.InstitutionCourse
import repository.institutions.InstitutionCourseRepository
import services.institutions.InstitutionCourseService

import scala.concurrent.Future

class InstitutionCourseServiceImpl extends InstitutionCourseService {
  override def getEntity(institutionId: String, courseId: String): Future[Option[InstitutionCourse]] =
    InstitutionCourseRepository.apply.getEntity(institutionId, courseId)

  override def getEntitiesForInstitution(institutionId: String): Future[Seq[InstitutionCourse]] =
    InstitutionCourseRepository.apply.getEntitiesForInstitution(institutionId)

  override def saveEntity(entity: InstitutionCourse): Future[Option[InstitutionCourse]] =
    InstitutionCourseRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[InstitutionCourse]] =
    InstitutionCourseRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[InstitutionCourse]] = ???

  override def deleteEntity(entity: InstitutionCourse): Future[Boolean] =
    InstitutionCourseRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = InstitutionCourseRepository.apply.createTable
}

package repository.institutions.impl.cockroachdb

import domain.institutions.InstitutionCourse
import repository.institutions.InstitutionCourseRepository
import repository.institutions.impl.cockroachdb.tables.{InstitutionCourseTable, InstitutionCourseTableCreate}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class InstitutionCourseRepositoryImpl extends InstitutionCourseRepository {
  override def getEntity(institutionId: String, courseId: String): Future[Option[InstitutionCourse]] =
    InstitutionCourseTable.getEntity(institutionId, courseId)

  override def getEntitiesForInstitution(institutionId: String): Future[Seq[InstitutionCourse]] =
    InstitutionCourseTable.getEntitiesForInstitution(institutionId)

  override def saveEntity(entity: InstitutionCourse): Future[Option[InstitutionCourse]] =
    InstitutionCourseTable.saveEntity(entity)

  override def getEntities: Future[Seq[InstitutionCourse]] =
    InstitutionCourseTable.getEntities

  override def getEntity(id: String): Future[Option[InstitutionCourse]] = ???

  override def deleteEntity(entity: InstitutionCourse): Future[Boolean] =
    InstitutionCourseTable.deleteEntity(entity.institutionId, entity.courseId).map(value => value.isValidInt)

  override def createTable: Future[Boolean] =
    Future.successful(InstitutionCourseTableCreate.createTable)
}

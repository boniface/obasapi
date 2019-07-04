package repository.institutions.Impl.cockroachdb

import domain.institutions.School
import repository.institutions.Impl.cockroachdb.tables.SchoolTable
import repository.institutions.SchoolRepository

import scala.concurrent.Future

class SchoolRepositoryImpl  extends SchoolRepository{

  override def saveEntity(entity: School): Future[Boolean] = {
    Future.successful(SchoolTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[School]] = {
    SchoolTable.getEntities
  }

  override def getEntity(schoolId: String): Future[Option[School]] = {
    SchoolTable.getEntity(schoolId)
  }

  override def deleteEntity(entity: School): Future[Boolean] = {
    Future.successful(SchoolTable.deleteEntity(entity.schoolId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(SchoolTable.createTable)
  }
}

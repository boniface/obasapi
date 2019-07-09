package repository.institutions.impl.cockroachdb

import domain.institutions.School
import repository.institutions.SchoolRepository
import repository.institutions.impl.cockroachdb.tables.SchoolTable

import scala.concurrent.Future

class SchoolRepositoryImpl  extends SchoolRepository{

  override def saveEntity(entity: School): Future[Boolean] = {
    Future.successful(SchoolTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[School]] = {
    SchoolTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[School]] = {
    SchoolTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: School): Future[Boolean] = {
    Future.successful(SchoolTable.deleteEntity(entity.schoolId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(SchoolTable.createTable)
  }
}

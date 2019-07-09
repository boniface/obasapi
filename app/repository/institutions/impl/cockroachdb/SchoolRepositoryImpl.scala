package repository.institutions.impl.cockroachdb

import domain.institutions.School
import repository.institutions.SchoolRepository
import repository.institutions.impl.cockroachdb.tables.SchoolTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class SchoolRepositoryImpl  extends SchoolRepository{

  override def saveEntity(entity: School): Future[Boolean] = {
    SchoolTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[School]] = {
    SchoolTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[School]] = {
    SchoolTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: School): Future[Boolean] = {
    SchoolTable.deleteEntity(entity.schoolId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(SchoolTable.createTable)
  }
}


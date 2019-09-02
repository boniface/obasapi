package repository.institutions.impl.cockroachdb

import domain.institutions.School
import repository.institutions.SchoolRepository
import repository.institutions.impl.cockroachdb.tables.SchoolTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class SchoolRepositoryImpl  extends SchoolRepository{

  override def saveEntity(entity: School): Future[Option[School]] = {
    SchoolTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[School]] = {
    SchoolTable.getEntities
  }

  override def getEntity(schoolId: String): Future[Option[School]] = {
    SchoolTable.getEntity(schoolId)
  }

  override def deleteEntity(entity: School): Future[Boolean] = {
    SchoolTable.deleteEntity(entity.schoolId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(SchoolTable.createTable)
  }
}


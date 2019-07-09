package repository.institutions.impl.cockroachdb

import domain.institutions.University
import repository.institutions.impl.cockroachdb.tables.UniversityTable
import repository.institutions.UniversityRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UniversityRepositoryImpl  extends UniversityRepository{

  override def saveEntity(entity: University): Future[Boolean] = {
    UniversityTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[University]] = {
    UniversityTable.getEntities
  }

  override def getEntity(universityId: String): Future[Option[University]] = {
    UniversityTable.getEntity(universityId)
  }

  override def deleteEntity(entity: University): Future[Boolean] = {
    UniversityTable.deleteEntity(entity.universityId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UniversityTable.createTable)
  }
}


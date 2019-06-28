package repository.institutions.Impl.cockroachdb

import domain.institutions.University
import repository.institutions.Impl.cockroachdb.tables.UniversityTable
import repository.institutions.UniversityRepository

import scala.concurrent.Future

class UniversityRepositoryImpl  extends UniversityRepository{

  override def saveEntity(entity: University): Future[Boolean] = {
    Future.successful(UniversityTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[University]] = {
    UniversityTable.getEntities
  }

  override def getEntity(universityId: String): Future[Option[University]] = {
    UniversityTable.getEntity(universityId)
  }

  override def deleteEntity(entity: University): Future[Boolean] = {
    Future.successful(UniversityTable.deleteEntity(entity.universityId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UniversityTable.createTable)
  }
}

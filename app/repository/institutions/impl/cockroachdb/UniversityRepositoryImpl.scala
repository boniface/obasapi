package repository.institutions.impl.cockroachdb

import domain.institutions.University
import repository.institutions.impl.cockroachdb.tables.UniversityTable
import repository.institutions.UniversityRepository

import scala.concurrent.Future

class UniversityRepositoryImpl  extends UniversityRepository{

  override def saveEntity(entity: University): Future[Boolean] = {
    Future.successful(UniversityTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[University]] = {
    UniversityTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[University]] = {
    UniversityTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: University): Future[Boolean] = {
    Future.successful(UniversityTable.deleteEntity(entity.universityId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UniversityTable.createTable)
  }
}

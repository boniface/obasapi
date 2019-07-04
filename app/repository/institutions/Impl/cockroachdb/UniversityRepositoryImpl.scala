package repository.institutions.Impl.cockroachdb

import domain.institutions.University
import repository.institutions.Impl.cockroachdb.tables.UniversityTable
import repository.institutions.UniversityRepository

import scala.concurrent.Future

class UniversityRepositoryImpl  extends UniversityRepository{

  override def saveEntity(entity: University): Future[Boolean] = {
    UniversityRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[University]] = {
    UniversityRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[University]] = {
    UniversityRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: University): Future[Boolean] = {
    UniversityRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UniversityRepository.roach.createTable
  }
}

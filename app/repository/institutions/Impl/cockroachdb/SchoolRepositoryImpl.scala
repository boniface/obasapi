package repository.institutions.Impl.cockroachdb

import domain.institutions.School
import repository.institutions.SchoolRepository

import scala.concurrent.Future

class SchoolRepositoryImpl  extends SchoolRepository{

  override def saveEntity(entity: School): Future[Boolean] = {
    SchoolRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[School]] = {
    SchoolRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[School]] = {
    SchoolRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: School): Future[Boolean] = {
    SchoolRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    SchoolRepository.roach.createTable
  }
}

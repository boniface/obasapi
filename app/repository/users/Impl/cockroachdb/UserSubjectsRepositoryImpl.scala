package repository.users.Impl.cockroachdb

import domain.users.UserSubjects
import repository.users.Impl.cockroachdb.tables.UserSubjectsTable
import repository.users.UserSubjectsRepository

import scala.concurrent.Future

class UserSubjectsRepositoryImpl  extends UserSubjectsRepository{

  override def saveEntity(entity: UserSubjects): Future[Boolean] = {
    UserSubjectsRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserSubjects]] = {
    UserSubjectsRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserSubjects]] = {
    UserSubjectsRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserSubjects): Future[Boolean] = {
    UserSubjectsRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UserSubjectsRepository.roach.createTable
  }
}


package repository.users.Impl.cockroachdb

import domain.users.UserResults
import repository.users.Impl.cockroachdb.tables.UserResultsTable
import repository.users.UserResultsRepository

import scala.concurrent.Future

class UserResultsRepositoryImpl  extends UserResultsRepository{

  override def saveEntity(entity: UserResults): Future[Boolean] = {
    UserResultsRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserResults]] = {
    UserResultsRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserResults]] = {
    UserResultsRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserResults): Future[Boolean] = {
    UserResultsRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UserResultsRepository.roach.createTable
  }
}

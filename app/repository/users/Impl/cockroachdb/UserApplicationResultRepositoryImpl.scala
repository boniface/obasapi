package repository.users.Impl.cockroachdb

import domain.users.UserApplicationResult
import repository.users.UserApplicationResultRepository

import scala.concurrent.Future

class UserApplicationResultRepositoryImpl  extends UserApplicationResultRepository{

  override def saveEntity(entity: UserApplicationResult): Future[Boolean] = {
    UserApplicationResultRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserApplicationResult]] = {
    UserApplicationResultRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserApplicationResult]] = {
    UserApplicationResultRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserApplicationResult): Future[Boolean] = {
    UserApplicationResultRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UserApplicationResultRepository.roach.createTable
  }
}

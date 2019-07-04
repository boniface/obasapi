package repository.users.Impl.cockroachdb

import domain.users.UserRelative
import repository.users.Impl.cockroachdb.tables.UserRelativeTable
import repository.users.UserRelativeRepository

import scala.concurrent.Future

class UserRelativeRepositoryImpl  extends UserRelativeRepository{

  override def saveEntity(entity: UserRelative): Future[Boolean] = {
    UserRelativeRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserRelative]] = {
    UserRelativeRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserRelative]] = {
    UserRelativeRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserRelative): Future[Boolean] = {
    UserRelativeRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UserRelativeRepository.roach.createTable
  }
}

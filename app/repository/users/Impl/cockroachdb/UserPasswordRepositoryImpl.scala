package repository.users.Impl.cockroachdb

import domain.users.UserPassword
import repository.users.Impl.cockroachdb.tables.UserPasswordTable
import repository.users.UserPasswordRepository

import scala.concurrent.Future

class UserPasswordRepositoryImpl  extends UserPasswordRepository{

  override def saveEntity(entity: UserPassword): Future[Boolean] = {
    UserPasswordRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserPassword]] = {
    UserPasswordRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserPassword]] = {
    UserPasswordRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserPassword): Future[Boolean] = {
    UserPasswordRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UserPasswordRepository.roach.createTable
  }
}

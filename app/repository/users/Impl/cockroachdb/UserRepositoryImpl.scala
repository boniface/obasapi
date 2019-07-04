package repository.users.Impl.cockroachdb

import domain.users.User
import repository.users.Impl.cockroachdb.tables.UserTable
import repository.users.UserRepository

import scala.concurrent.Future

class UserRepositoryImpl  extends UserRepository{

  override def saveEntity(entity: User): Future[Boolean] = {
    UserRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[User]] = {
    UserRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[User]] = {
    UserRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: User): Future[Boolean] = {
    UserRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UserRepository.roach.createTable
  }
}


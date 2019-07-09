package repository.users.impl.cockroachdb

import domain.users.User
import repository.users.impl.cockroachdb.tables.UserTable
import repository.users.UserRepository

import scala.concurrent.Future

class UserRepositoryImpl  extends UserRepository{

  override def saveEntity(entity: User): Future[Boolean] = {
    Future.successful(UserTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[User]] = {
    UserTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[User]] = {
    UserTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: User): Future[Boolean] = {
    Future.successful(UserTable.deleteEntity(entity.email).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserTable.createTable)
  }
}


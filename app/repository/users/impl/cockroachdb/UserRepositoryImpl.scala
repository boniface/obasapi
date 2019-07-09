package repository.users.impl.cockroachdb

import domain.users.User
import repository.users.impl.cockroachdb.tables.UserTable
import repository.users.UserRepository

import scala.concurrent.Future

class UserRepositoryImpl  extends UserRepository{

  override def saveEntity(entity: User): Future[Boolean] = {
    UserTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[User]] = {
    UserTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[User]] = {
    UserTable.getEntity(email)
  }

  override def deleteEntity(email: String): Future[Boolean] = {
    UserTable.deleteEntity(entity.email)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserTable.createTable)
  }
}

package repository.users.Impl.cockroachdb

import domain.users.User
import repository.users.Impl.cockroachdb.tables.UserTable
import repository.users.UserRepository

import scala.concurrent.Future

class UserRepositoryImpl  extends UserRepository{

  override def saveEntity(entity: User): Future[Boolean] = {
    Future.successful(UserTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[User]] = {
    UserTable.getEntities
  }

  override def getEntity(email: String): Future[Option[User]] = {
    UserTable.getEntity(email)
  }

  override def deleteEntity(entity: User): Future[Boolean] = {
    Future.successful(UserTable.deleteEntity(entity.email).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserTable.createTable)
  }
}


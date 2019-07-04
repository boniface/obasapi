package repository.users.Impl.cockroachdb

import domain.users.UserPassword
import repository.users.Impl.cockroachdb.tables.UserPasswordTable
import repository.users.UserPasswordRepository

import scala.concurrent.Future

class UserPasswordRepositoryImpl  extends UserPasswordRepository{

  override def saveEntity(entity: UserPassword): Future[Boolean] = {
    Future.successful(UserPasswordTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserPassword]] = {
    UserPasswordTable.getEntities
  }

  override def getEntity(userPasswordId: String): Future[Option[UserPassword]] = {
    UserPasswordTable.getEntity(userPasswordId)
  }

  override def deleteEntity(entity: UserPassword): Future[Boolean] = {
    Future.successful(UserPasswordTable.deleteEntity(entity.userPasswordId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserPasswordTable.createTable)
  }
}

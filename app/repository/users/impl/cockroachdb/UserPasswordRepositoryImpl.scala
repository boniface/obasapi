package repository.users.impl.cockroachdb

import domain.users.UserPassword
import repository.users.impl.cockroachdb.tables.UserPasswordTable
import repository.users.UserPasswordRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserPasswordRepositoryImpl  extends UserPasswordRepository{

  override def saveEntity(entity: UserPassword): Future[Boolean] = {
    UserPasswordTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[UserPassword]] = {
    UserPasswordTable.getEntities
  }

  override def getEntity(userPasswordId: String): Future[Option[UserPassword]] = {
    UserPasswordTable.getEntity(userPasswordId)
  }

  override def deleteEntity(entity: UserPassword): Future[Boolean] = {
    UserPasswordTable.deleteEntity(entity.userId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserPasswordTable.createTable)
  }
}



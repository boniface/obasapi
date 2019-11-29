package repository.users.impl.cockroachdb

import domain.users.UserApplication
import repository.users.UserApplicationRepository
import repository.users.impl.cockroachdb.tables.UserApplicationTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserApplicationRepositoryImpl  extends UserApplicationRepository{

  override def saveEntity(entity: UserApplication): Future[Option[UserApplication]] = {
    UserApplicationTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserApplication]] = {
    UserApplicationTable.getEntities
  }

  override def getEntity(userApplicationResultId: String): Future[Option[UserApplication]] = {
    UserApplicationTable.getEntity(userApplicationResultId)
  }

  override def deleteEntity(entity: UserApplication): Future[Boolean] = {
    UserApplicationTable.deleteEntity(entity.userId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserApplicationTable.createTable)
  }
}



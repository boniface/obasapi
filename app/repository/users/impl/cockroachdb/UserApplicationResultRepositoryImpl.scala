package repository.users.impl.cockroachdb

import domain.users.UserApplicationResult
import repository.users.UserApplicationResultRepository
import repository.users.impl.cockroachdb.tables.UserApplicationResultTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserApplicationResultRepositoryImpl  extends UserApplicationResultRepository{

  override def saveEntity(entity: UserApplicationResult): Future[Boolean] = {
    UserApplicationResultTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[UserApplicationResult]] = {
    UserApplicationResultTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserApplicationResult]] = {
    UserApplicationResultTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserApplicationResult): Future[Boolean] = {
    UserApplicationResultTable.deleteEntity(entity.userApplicationResultId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserApplicationResultTable.createTable)
  }
}



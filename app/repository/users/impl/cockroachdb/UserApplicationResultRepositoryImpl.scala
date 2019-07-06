package repository.users.impl.cockroachdb

import domain.users.UserApplicationResult
import repository.users.UserApplicationResultRepository
import repository.users.impl.cockroachdb.tables.UserApplicationResultTable

import scala.concurrent.Future

class UserApplicationResultRepositoryImpl  extends UserApplicationResultRepository{

  override def saveEntity(entity: UserApplicationResult): Future[Boolean] = {
    Future.successful(UserApplicationResultTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserApplicationResult]] = {
    UserApplicationResultTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserApplicationResult]] = {
    UserApplicationResultTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserApplicationResult): Future[Boolean] = {
    Future.successful(UserApplicationResultTable.deleteEntity(entity.userApplicationResultId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserApplicationResultTable.createTable)
  }
}

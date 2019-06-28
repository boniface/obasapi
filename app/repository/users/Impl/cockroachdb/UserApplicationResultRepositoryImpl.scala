package repository.users.Impl.cockroachdb

import domain.users.UserApplicationResult
import repository.users.Impl.cockroachdb.tables.UserApplicationResultTable
import repository.users.UserApplicationResultRepository

import scala.concurrent.Future

class UserApplicationResultRepositoryImpl  extends UserApplicationResultRepository{

  override def saveEntity(entity: UserApplicationResult): Future[Boolean] = {
    Future.successful(UserApplicationResultTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserApplicationResult]] = {
    UserApplicationResultTable.getEntities
  }

  override def getEntity(userApplicationResultId: String): Future[Option[UserApplicationResult]] = {
    UserApplicationResultTable.getEntity(userApplicationResultId)
  }

  override def deleteEntity(entity: UserApplicationResult): Future[Boolean] = {
    Future.successful(UserApplicationResultTable.deleteEntity(entity.userApplicationResultId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserApplicationResultTable.createTable)
  }
}

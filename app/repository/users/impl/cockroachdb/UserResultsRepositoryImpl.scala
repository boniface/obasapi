package repository.users.impl.cockroachdb

import domain.users.UserResults
import repository.users.impl.cockroachdb.tables.UserResultsTable
import repository.users.UserResultsRepository

import scala.concurrent.Future

class UserResultsRepositoryImpl  extends UserResultsRepository{

  override def saveEntity(entity: UserResults): Future[Boolean] = {
    Future.successful(UserResultsTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserResults]] = {
    UserResultsTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserResults]] = {
    UserResultsTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserResults): Future[Boolean] = {
    Future.successful(UserResultsTable.deleteEntity(entity.userResultsId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserResultsTable.createTable)
  }
}

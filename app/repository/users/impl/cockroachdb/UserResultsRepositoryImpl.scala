package repository.users.impl.cockroachdb

import domain.users.UserResults
import repository.users.impl.cockroachdb.tables.UserResultsTable
import repository.users.UserResultsRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserResultsRepositoryImpl  extends UserResultsRepository{

  override def saveEntity(entity: UserResults): Future[Option[UserResults]] = {
    UserResultsTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserResults]] = {
    UserResultsTable.getEntities
  }

  override def getEntity(userResultsId: String): Future[Option[UserResults]] = {
    UserResultsTable.getEntity(userResultsId)
  }

  override def deleteEntity(entity: UserResults): Future[Boolean] = {
    UserResultsTable.deleteEntity(entity.userResultsId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserResultsTable.createTable)
  }
}


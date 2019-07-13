package repository.users.impl.cockroachdb

import domain.users.UserRelative
import repository.users.impl.cockroachdb.tables.UserRelativeTable
import repository.users.UserRelativeRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserRelativeRepositoryImpl  extends UserRelativeRepository{

  override def saveEntity(entity: UserRelative): Future[Boolean] = {
    UserRelativeTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[UserRelative]] = {
    UserRelativeTable.getEntities
  }

  override def getEntity(userRelativeId: String): Future[Option[UserRelative]] = {
    UserRelativeTable.getEntity(userRelativeId)
  }

  override def deleteEntity(entity: UserRelative): Future[Boolean] = {
    UserRelativeTable.deleteEntity(entity.userRelativeId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserRelativeTable.createTable)
  }
}


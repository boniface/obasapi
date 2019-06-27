package repository.users.Impl.cockroachdb

import domain.users.UserRelative
import repository.users.Impl.cockroachdb.tables.UserRelativeTable
import repository.users.UserRelativeRepository

import scala.concurrent.Future

class UserRelativeRepositoryImpl  extends UserRelativeRepository{

  override def saveEntity(entity: UserRelative): Future[Boolean] = {
    Future.successful(UserRelativeTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserRelative]] = {
    UserRelativeTable.getEntities
  }

  override def getEntity(userRelativeId: String): Future[Option[UserRelative]] = {
    UserRelativeTable.getEntity(userRelativeId)
  }

  override def deleteEntity(entity: UserRelative): Future[Boolean] = {
    Future.successful(UserRelativeTable.deleteEntity(entity.userRelativeId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserRelativeTable.createTable)
  }
}

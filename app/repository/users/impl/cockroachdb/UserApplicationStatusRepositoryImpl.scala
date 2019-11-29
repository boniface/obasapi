package repository.users.impl.cockroachdb

import domain.users.UserApplicationStatus
import repository.users.UserApplicationStatusRepository
import repository.users.impl.cockroachdb.tables.UserApplicationStatusTable

import scala.concurrent.Future

class UserApplicationStatusRepositoryImpl extends UserApplicationStatusRepository {

  override def saveEntity(entity: UserApplicationStatus): Future[Option[UserApplicationStatus]] = {
    UserApplicationStatusTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserApplicationStatus]] = {
    UserApplicationStatusTable.getEntities
  }

  override def getEntity(statusId: String): Future[Option[UserApplicationStatus]] = {
    UserApplicationStatusTable.getEntity(statusId)
  }

  override def deleteEntity(entity: UserApplicationStatus): Future[Boolean] = {
    UserApplicationStatusTable.deleteEntity(entity.statusId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserApplicationStatusTable.createTable)
  }
}

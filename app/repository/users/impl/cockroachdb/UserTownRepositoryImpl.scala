package repository.users.impl.cockroachdb

import domain.users.UserTown
import repository.users.UserTownRepository
import repository.users.impl.cockroachdb.tables.UserTownTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserTownRepositoryImpl extends UserTownRepository {
  override def saveEntity(entity: UserTown): Future[Option[UserTown]] =
    UserTownTable.saveEntity(entity)

  override def getEntities: Future[Seq[UserTown]] = UserTownTable.getEntities

  override def getEntity(id: String): Future[Option[UserTown]] = UserTownTable.getEntity(id)

  override def deleteEntity(entity: UserTown): Future[Boolean] = UserTownTable.deleteEntity(entity.userId).map(value => value.isValidInt)

  override def createTable: Future[Boolean] = Future.successful(UserTownTable.createTable)
}

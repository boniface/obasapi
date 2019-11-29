package repository.users.impl.cockroachdb

import domain.users.UserApplicationStatus
import repository.users.UserApplicationStatusRepository

import scala.concurrent.Future

class UserApplicationStatusRepositoryImpl extends UserApplicationStatusRepository {
  override def saveEntity(entity: UserApplicationStatus): Future[Option[UserApplicationStatus]] = ???

  override def getEntities: Future[Seq[UserApplicationStatus]] = ???

  override def getEntity(id: String): Future[Option[UserApplicationStatus]] = ???

  override def deleteEntity(entity: UserApplicationStatus): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}

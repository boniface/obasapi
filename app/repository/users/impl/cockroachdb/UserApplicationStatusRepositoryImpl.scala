package repository.users.impl.cockroachdb

import domain.users.UserApplicationStatus
import repository.users.UserApplicationStatusRepository
import repository.users.impl.cockroachdb.tables.{UserApplicationStatusTable, UserApplicationStatusTableCreate}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserApplicationStatusRepositoryImpl extends UserApplicationStatusRepository {

  override def getEntitiesForApplication(applicationId: String): Future[Seq[UserApplicationStatus]] =
    UserApplicationStatusTable.getEntitiesForApplication(applicationId)

  override def saveEntity(entity: UserApplicationStatus): Future[Option[UserApplicationStatus]] =
    UserApplicationStatusTable.saveEntity(entity)

  override def getEntities: Future[Seq[UserApplicationStatus]] =
    UserApplicationStatusTable.getEntities

  override def getEntity(id: String): Future[Option[UserApplicationStatus]] = ???

  override def deleteEntity(entity: UserApplicationStatus): Future[Boolean] = ???

  override def createTable: Future[Boolean] =
    Future.successful(UserApplicationStatusTableCreate.createTable)

  override def getEntitiesForAppnStatus(applicationId: String, statusId: String): Future[Seq[UserApplicationStatus]] =
    UserApplicationStatusTable.getEntitiesForAppnStatus(applicationId, statusId)

  override def getLatestForAppnStatus(applicationId: String, statusId: String): Future[Option[UserApplicationStatus]] =
    UserApplicationStatusTable.getLatestForAppnStatus(applicationId, statusId)

  override def getLatestForApplication(applicationId: String): Future[Option[UserApplicationStatus]] =
    UserApplicationStatusTable.getLatestForApplication(applicationId)
}

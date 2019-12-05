package repository.application.impl.cockcroachdb

import domain.application.ApplicationStatus
import repository.application.ApplicationStatusRepository
import repository.application.impl.cockcroachdb.tables.{UserApplicationStatusTable, UserApplicationStatusTableCreate}

import scala.concurrent.Future

class ApplicationStatusRepositoryImpl extends ApplicationStatusRepository {

  override def getEntitiesForApplication(applicationId: String): Future[Seq[ApplicationStatus]] =
    UserApplicationStatusTable.getEntitiesForApplication(applicationId)

  override def saveEntity(entity: ApplicationStatus): Future[Option[ApplicationStatus]] =
    UserApplicationStatusTable.saveEntity(entity)

  override def getEntities: Future[Seq[ApplicationStatus]] =
    UserApplicationStatusTable.getEntities

  override def getEntity(id: String): Future[Option[ApplicationStatus]] = ???

  override def deleteEntity(entity: ApplicationStatus): Future[Boolean] = ???

  override def createTable: Future[Boolean] =
    Future.successful(UserApplicationStatusTableCreate.createTable)

  override def getEntitiesForAppnStatus(applicationId: String, statusId: String): Future[Seq[ApplicationStatus]] =
    UserApplicationStatusTable.getEntitiesForAppnStatus(applicationId, statusId)

  override def getLatestForAppnStatus(applicationId: String, statusId: String): Future[Option[ApplicationStatus]] =
    UserApplicationStatusTable.getLatestForAppnStatus(applicationId, statusId)

  override def getLatestForApplication(applicationId: String): Future[Option[ApplicationStatus]] =
    UserApplicationStatusTable.getLatestForApplication(applicationId)
}

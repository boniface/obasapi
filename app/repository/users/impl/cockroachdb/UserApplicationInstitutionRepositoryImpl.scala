package repository.users.impl.cockroachdb

import domain.users.UserApplicationInstitution
import repository.users.UserApplicationInstitutionRepository
import repository.users.impl.cockroachdb.tables.{UserApplicationInstitutionTable, UserApplicationInstitutionTableCreate}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserApplicationInstitutionRepositoryImpl extends UserApplicationInstitutionRepository {
  override def getEntity(userId: String, applicationId: String): Future[Option[UserApplicationInstitution]] =
    UserApplicationInstitutionTable.getEntity(userId, applicationId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserApplicationInstitution]] =
    UserApplicationInstitutionTable.getEntitiesForUser(userId)

  override def saveEntity(entity: UserApplicationInstitution): Future[Option[UserApplicationInstitution]] =
    UserApplicationInstitutionTable.saveEntity(entity)

  override def getEntities: Future[Seq[UserApplicationInstitution]] =
    UserApplicationInstitutionTable.getEntities

  override def getEntity(id: String): Future[Option[UserApplicationInstitution]] = ???

  override def deleteEntity(entity: UserApplicationInstitution): Future[Boolean] =
    UserApplicationInstitutionTable.deleteEntity(entity.userId, entity.applicationId).map(value => value.isValidInt)

  override def createTable: Future[Boolean] =
    Future.successful(UserApplicationInstitutionTableCreate.createTable)
}

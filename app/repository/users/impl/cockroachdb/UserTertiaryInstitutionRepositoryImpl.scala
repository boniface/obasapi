package repository.users.impl.cockroachdb

import domain.users.UserTertiaryInstitution
import repository.users.UserTertiaryInstitutionRepository
import repository.users.impl.cockroachdb.tables.{UserTertiaryInstitutionTable, UserTertiaryInstitutionTableCreate}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserTertiaryInstitutionRepositoryImpl extends UserTertiaryInstitutionRepository {
  override def getEntity(userId: String, applicationId: String): Future[Option[UserTertiaryInstitution]] =
    UserTertiaryInstitutionTable.getEntity(userId, applicationId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserTertiaryInstitution]] =
    UserTertiaryInstitutionTable.getEntitiesForUser(userId)

  override def saveEntity(entity: UserTertiaryInstitution): Future[Option[UserTertiaryInstitution]] =
    UserTertiaryInstitutionTable.saveEntity(entity)

  override def getEntities: Future[Seq[UserTertiaryInstitution]] =
    UserTertiaryInstitutionTable.getEntities

  override def getEntity(id: String): Future[Option[UserTertiaryInstitution]] = ???

  override def deleteEntity(entity: UserTertiaryInstitution): Future[Boolean] =
    UserTertiaryInstitutionTable.deleteEntity(entity.userId, entity.applicationId).map(value => value.isValidInt)

  override def createTable: Future[Boolean] =
    Future.successful(UserTertiaryInstitutionTableCreate.createTable)
}

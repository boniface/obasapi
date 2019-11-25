package repository.users.impl.cockroachdb

import domain.users.UserInstitution
import repository.users.impl.cockroachdb.tables.{UserInstitutionTable, UserInstitutionTableCreate}
import repository.users.UserInstitutionRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserInstitutionRepositoryImpl  extends UserInstitutionRepository{

  override def saveEntity(entity: UserInstitution): Future[Option[UserInstitution]] = {
    UserInstitutionTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserInstitution]] = {
    UserInstitutionTable.getEntities
  }

  override def deleteEntity(entity: UserInstitution): Future[Boolean] = {
    UserInstitutionTable.deleteEntity(entity.userId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserInstitutionTableCreate.createTable)
  }

  override def getEntity(userId: String, institutionId: String): Future[Option[UserInstitution]] =
    UserInstitutionTable.getEntity(userId, institutionId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserInstitution]] =
    UserInstitutionTable.getEntitiesForUser(userId)

  override def getEntity(id: String): Future[Option[UserInstitution]] = ???
}



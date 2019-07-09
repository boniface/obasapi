package repository.users.impl.cockroachdb

import domain.users.UserInstitution
import repository.users.impl.cockroachdb.tables.UserInstitutionTable
import repository.users.UserInstitutionRepository

import scala.concurrent.Future

class UserInstitutionRepositoryImpl  extends UserInstitutionRepository{

  override def saveEntity(entity: UserInstitution): Future[Boolean] = {
    Future.successful(UserInstitutionTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserInstitution]] = {
    UserInstitutionTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserInstitution]] = {
    UserInstitutionTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserInstitution): Future[Boolean] = {
    Future.successful(UserInstitutionTable.deleteEntity(entity.userInstitutionId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserInstitutionTable.createTable)
  }
}

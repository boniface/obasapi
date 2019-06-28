package repository.users.Impl.cockroachdb

import domain.users.UserInstitution
import repository.users.Impl.cockroachdb.tables.UserInstitutionTable
import repository.users.UserInstitutionRepository

import scala.concurrent.Future

class UserInstitutionRepositoryImpl  extends UserInstitutionRepository{

  override def saveEntity(entity: UserInstitution): Future[Boolean] = {
    Future.successful(UserInstitutionTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserInstitution]] = {
    UserInstitutionTable.getEntities
  }

  override def getEntity(userInstitutionId: String): Future[Option[UserInstitution]] = {
    UserInstitutionTable.getEntity(userInstitutionId)
  }

  override def deleteEntity(entity: UserInstitution): Future[Boolean] = {
    Future.successful(UserInstitutionTable.deleteEntity(entity.userInstitutionId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserInstitutionTable.createTable)
  }
}

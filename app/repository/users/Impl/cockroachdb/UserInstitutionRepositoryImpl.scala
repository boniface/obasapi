package repository.users.Impl.cockroachdb

import domain.users.UserInstitution
import repository.users.Impl.cockroachdb.tables.UserInstitutionTable
import repository.users.UserInstitutionRepository

import scala.concurrent.Future

class UserInstitutionRepositoryImpl  extends UserInstitutionRepository{

  override def saveEntity(entity: UserInstitution): Future[Boolean] = {
    UserInstitutionRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserInstitution]] = {
    UserInstitutionRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserInstitution]] = {
    UserInstitutionRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserInstitution): Future[Boolean] = {
    UserInstitutionRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UserInstitutionRepository.roach.createTable
  }
}

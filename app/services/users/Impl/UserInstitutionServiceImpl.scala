package services.users.Impl

import domain.users.UserInstitution
import repository.users.UserInstitutionRepository
import services.users.UserInstitutionService

import scala.concurrent.Future

class UserInstitutionServiceImpl extends UserInstitutionService {

  override def saveEntity(entity: UserInstitution): Future[Option[UserInstitution]] =
    UserInstitutionRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserInstitution]] =
    UserInstitutionRepository.roach.getEntities

  override def deleteEntity(entity: UserInstitution): Future[Boolean] =
    UserInstitutionRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserInstitutionRepository.roach.createTable

  override def getEntity(userId: String, institutionId: String): Future[Option[UserInstitution]] =
    UserInstitutionRepository.roach.getEntity(userId, institutionId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserInstitution]] =
    UserInstitutionRepository.roach.getEntitiesForUser(userId)

  override def getEntity(id: String): Future[Option[UserInstitution]] = ???
}

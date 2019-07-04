package services.users.Impl.cockroachdb

import domain.users.UserInstitution
import services.users.UserInstitutionService

import scala.concurrent.Future

class UserInstitutionServiceImpl extends UserInstitutionService {

  override def saveEntity(entity: UserInstitution): Future[Boolean] =
    UserInstitutionService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserInstitution]] =
    UserInstitutionService.roach.getEntities

  override def getEntity(userAddressId: String): Future[Option[UserInstitution]] =
    UserInstitutionService.roach.getEntity(userAddressId)

  override def deleteEntity(entity: UserInstitution): Future[Boolean] =
    UserInstitutionService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserInstitutionService.roach.createTable

}

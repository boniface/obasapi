package services.users.Impl.cockroachdb

import domain.users.UserRole
import services.users.UserRoleService

import scala.concurrent.Future

class UserRoleServiceImpl extends UserRoleService {

  override def saveEntity(entity: UserRole): Future[Boolean] =
    UserRoleService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserRole]] =
    UserRoleService.roach.getEntities

  override def getEntity(userAddressId: String): Future[Option[UserRole]] =
    UserRoleService.roach.getEntity(userAddressId)

  override def deleteEntity(entity: UserRole): Future[Boolean] =
    UserRoleService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserRoleService.roach.createTable

}

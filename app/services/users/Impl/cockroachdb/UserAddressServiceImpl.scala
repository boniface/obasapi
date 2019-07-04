package services.users.Impl.cockroachdb

import domain.users.UserAddress
import services.users.UserAddressService

import scala.concurrent.Future

class UserAddressServiceImpl extends UserAddressService {

  override def saveEntity(entity: UserAddress): Future[Boolean] =
    UserAddressService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserAddress]] =
    UserAddressService.roach.getEntities

  override def getEntity(userAddressId: String): Future[Option[UserAddress]] =
    UserAddressService.roach.getEntity(userAddressId)

  override def deleteEntity(entity: UserAddress): Future[Boolean] =
    UserAddressService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserAddressService.roach.createTable

}

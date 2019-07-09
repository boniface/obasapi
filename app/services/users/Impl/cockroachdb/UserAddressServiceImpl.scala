package services.users.Impl.cockroachdb

import domain.users.UserAddress
import repository.users.UserAddressRepository
import services.users.UserAddressService

import scala.concurrent.Future

class UserAddressServiceImpl extends UserAddressService {

  override def saveEntity(entity: UserAddress): Future[Boolean] =
    UserAddressRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserAddress]] =
    UserAddressRepository.roach.getEntities

  override def getEntity(userAddressId: String): Future[Option[UserAddress]] =
    UserAddressRepository.roach.getEntity(userAddressId)

  override def deleteEntity(entity: UserAddress): Future[Boolean] =
    UserAddressRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserAddressRepository.roach.createTable

}

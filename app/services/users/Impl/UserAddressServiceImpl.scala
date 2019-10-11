package services.users.Impl

import domain.users.UserAddress
import repository.users.UserAddressRepository
import services.users.UserAddressService

import scala.concurrent.Future

class UserAddressServiceImpl extends UserAddressService {

  override def saveEntity(entity: UserAddress): Future[Option[UserAddress]] =
    UserAddressRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserAddress]] =
    UserAddressRepository.roach.getEntities

  override def getEntity(userId: String, addressTypeId: String): Future[Option[UserAddress]] =
    UserAddressRepository.roach.getEntity(userId, addressTypeId)

  override def deleteEntity(entity: UserAddress): Future[Boolean] =
    UserAddressRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserAddressRepository.roach.createTable

  override def getEntityForUser(userId: String): Future[Seq[UserAddress]] =
    UserAddressRepository.roach.getEntityForUser(userId)

  override def getEntity(id: String): Future[Option[UserAddress]] = ???
}

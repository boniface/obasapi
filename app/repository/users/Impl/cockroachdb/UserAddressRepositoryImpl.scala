package repository.users.Impl.cockroachdb

import domain.users.UserAddress
import repository.users.UserAddressRepository

import scala.concurrent.Future

class UserAddressRepositoryImpl  extends UserAddressRepository{

  override def saveEntity(entity: UserAddress): Future[Boolean] = {
    UserAddressRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserAddress]] = {
    UserAddressRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserAddress]] = {
    UserAddressRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserAddress): Future[Boolean] = {
    UserAddressRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UserAddressRepository.roach.createTable
  }
}

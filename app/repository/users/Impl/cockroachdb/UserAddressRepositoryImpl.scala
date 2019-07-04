package repository.users.Impl.cockroachdb

import domain.users.UserAddress
import repository.users.Impl.cockroachdb.tables.UserAddressTable
import repository.users.UserAddressRepository

import scala.concurrent.Future

class UserAddressRepositoryImpl  extends UserAddressRepository{

  override def saveEntity(entity: UserAddress): Future[Boolean] = {
    Future.successful(UserAddressTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserAddress]] = {
    UserAddressTable.getEntities
  }

  override def getEntity(userAddressId: String): Future[Option[UserAddress]] = {
    UserAddressTable.getEntity(userAddressId)
  }

  override def deleteEntity(entity: UserAddress): Future[Boolean] = {
    Future.successful(UserAddressTable.deleteEntity(entity.userAddressId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserAddressTable.createTable)
  }
}

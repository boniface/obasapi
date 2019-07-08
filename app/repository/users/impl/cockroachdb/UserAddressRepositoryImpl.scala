package repository.users.impl.cockroachdb

import domain.users.UserAddress
import repository.users.UserAddressRepository
import repository.users.impl.cockroachdb.tables.UserAddressTable

import scala.concurrent.Future

class UserAddressRepositoryImpl  extends UserAddressRepository{

  override def saveEntity(entity: UserAddress): Future[Boolean] = {
    println("Did i get jere?",  entity)
    Future.successful(UserAddressTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserAddress]] = {
    UserAddressTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserAddress]] = {
    UserAddressTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserAddress): Future[Boolean] = {
    Future.successful(UserAddressTable.deleteEntity(entity.userAddressId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserAddressTable.createTable)
  }
}

package repository.users.impl.cockroachdb

import domain.users.UserAddress
import repository.users.UserAddressRepository
import repository.users.impl.cockroachdb.tables.{UserAddressTable, UserAddressTableCreate}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserAddressRepositoryImpl  extends UserAddressRepository{

  override def saveEntity(entity: UserAddress): Future[Option[UserAddress]] = {
    UserAddressTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserAddress]] = {
    UserAddressTable.getEntities
  }

  override def getEntity(userId: String, addressTypeId: String): Future[Option[UserAddress]] = {
    UserAddressTable.getEntity(userId, addressTypeId)
  }

  override def deleteEntity(entity: UserAddress): Future[Boolean] = {
    UserAddressTable.deleteEntity(entity.userId, entity.addressTypeId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserAddressTableCreate.createTable)
  }

  override def getEntityForUser(userId: String): Future[Seq[UserAddress]] =
    UserAddressTable.getEntityForUser(userId)

  override def getEntity(id: String): Future[Option[UserAddress]] = ???
}



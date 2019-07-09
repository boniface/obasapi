package repository.address.impl.cockcroachdb

import domain.address.AddressType
import repository.address.AddressTypeRepository
import repository.address.impl.cockcroachdb.tables.AddressTypeTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class AddressTypeRepositoryImpl extends AddressTypeRepository{
  override def saveEntity(entity: AddressType): Future[Boolean] ={
    AddressTypeTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[AddressType]] = {
    AddressTypeTable.getEntities
  }

  override def getEntity(addressTypeID: String): Future[Option[AddressType]] = {
    AddressTypeTable.getEntity(addressTypeID)
  }

  override def deleteEntity(entity: AddressType): Future[Boolean] = {
    AddressTypeTable.deleteEntity(entity.addressTypeID)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(AddressTypeTable.createTable)
  }
}

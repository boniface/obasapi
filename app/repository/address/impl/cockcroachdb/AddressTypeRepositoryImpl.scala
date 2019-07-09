package repository.address.impl.cockcroachdb

import domain.address.AddressType
import repository.address.AddressTypeRepository
import repository.address.impl.cockcroachdb.tables.AddressTypeTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class AddressTypeRepositoryImpl extends AddressTypeRepository{
  override def saveEntity(entity: AddressType): Future[Boolean] ={
    val result = AddressTypeTable.saveEntity(entity)
    result.map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[AddressType]] = {
    AddressTypeTable.getEntities
  }

  override def getEntity(addressTypeID: String): Future[Option[AddressType]] = {
    AddressTypeTable.getEntity(addressTypeID)
  }

  override def deleteEntity(entity: AddressType): Future[Boolean] = {
    Future.successful(AddressTypeTable.deleteEntity(entity.addressTypeID).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(AddressTypeTable.createTable)
  }
}

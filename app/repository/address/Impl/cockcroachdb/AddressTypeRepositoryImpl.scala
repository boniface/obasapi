package repository.address.Impl.cockcroachdb

import domain.address.AddressType
import repository.address.AddressTypeRepository
import repository.address.Impl.cockcroachdb.tables.AddressTypeTable

import scala.concurrent.Future

class AddressTypeRepositoryImpl extends AddressTypeRepository{
  override def saveEntity(entity: AddressType): Future[Boolean] ={
   AddressTypeRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[AddressType]] = {
    AddressTypeRepository.roach.getEntities
  }

  override def getEntity(addressTypeID: String): Future[Option[AddressType]] = {
    AddressTypeRepository.roach.getEntity(addressTypeID)
  }

  override def deleteEntity(entity: AddressType): Future[Boolean] = {
    AddressTypeRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    AddressTypeRepository.roach.createTable
  }
}

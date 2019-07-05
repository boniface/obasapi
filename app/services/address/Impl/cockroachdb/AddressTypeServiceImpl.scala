package services.address.Impl.cockroachdb

import domain.address.AddressType
import services.address.AddressTypeService

import scala.concurrent.Future

class AddressTypeServiceImpl extends AddressTypeService{


  override def saveEntity(entity: AddressType): Future[Boolean] = {
    AddressTypeService.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[AddressType]] = {
    AddressTypeService.roach.getEntities
  }

  override def getEntity(addressTypeID: String): Future[Option[AddressType]] = {
    AddressTypeService.roach.getEntity(addressTypeID)
  }

  override def deleteEntity(entity: AddressType): Future[Boolean] = {
    AddressTypeService.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    AddressTypeService.roach.createTable
  }
}

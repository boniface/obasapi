package services.address.Impl.cockroachdb

import domain.address.AddressType
import repository.address.AddressTypeRepository
import services.address.AddressTypeService

import scala.concurrent.Future

class AddressTypeServiceImpl extends AddressTypeService{


  override def saveEntity(entity: AddressType): Future[Option[AddressType]] =
    AddressTypeRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[AddressType]] = {
    println(" The Address Type is Requested Here ")
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

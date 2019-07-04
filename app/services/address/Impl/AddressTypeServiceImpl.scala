package services.address.Impl

import services.address.AddressTypeService
import domain.address.AddressType


import scala.concurrent.Future

class AddressTypeServiceImpl extends AddressTypeService{


  override def saveEntity(entity: AddressType): Future[Boolean] = ???

  override def getEntities: Future[Seq[AddressType]] = ???

  override def getEntity(addressTypeID: String): Future[Option[AddressType]] = ???

  override def deleteEntity(entity: AddressType): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}

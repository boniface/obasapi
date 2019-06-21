package services.address.Impl

import services.CrudService
import domain.address.AddressType
import javax.swing.text.html.parser.Entity
import services.address.AddressTypeService

import scala.concurrent.Future

class AddressTypeServiceImpl extends CrudService[AddressType ]{


  override def saveEntity(entity: AddressType): Future[Boolean] = ???

  override def getEntities: Future[Seq[AddressType]] = ???

  override def getEntity(id: String): Future[Option[AddressType]] = ???

  override def deleteEntity(entity: AddressType): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}

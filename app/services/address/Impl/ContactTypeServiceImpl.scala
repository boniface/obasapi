package services.address.Impl

import services.CrudService
import domain.address.ContactType

import scala.concurrent.Future

class ContactTypeServiceImpl extends CrudService[ContactType]{


  override def saveEntity(entity: ContactType): Future[Boolean] = ???

  override def getEntities: Future[Seq[ContactType]] = ???

  override def getEntity(ContactType: String): Future[Option[ContactType]] = ???

  override def deleteEntity(entity: ContactType): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}

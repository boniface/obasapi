package services.address.Impl.cockroachdb

import domain.address.ContactType
import services.address.ContactTypeService

import scala.concurrent.Future

class ContactTypeServiceImpl extends ContactTypeService{


  override def saveEntity(entity: ContactType): Future[Boolean] = {
    ContactTypeService.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[ContactType]] = {
    ContactTypeService.roach.getEntities
  }

  override def getEntity(contactTypeId: String): Future[Option[ContactType]] = {
    ContactTypeService.roach.getEntity(contactTypeId)
  }

  override def deleteEntity(entity: ContactType): Future[Boolean] = {
    ContactTypeService.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    ContactTypeService.roach.createTable
  }
}

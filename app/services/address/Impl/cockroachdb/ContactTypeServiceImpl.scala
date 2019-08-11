package services.address.Impl.cockroachdb

import domain.address.ContactType
import repository.address.ContactTypeRepository
import services.address.ContactTypeService


import scala.concurrent.Future

class ContactTypeServiceImpl extends ContactTypeService{


  override def saveEntity(entity: ContactType): Future[Option[ContactType]] =
    ContactTypeRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[ContactType]] = {
    ContactTypeRepository.roach.getEntities
  }

  override def getEntity(contactTypeId: String): Future[Option[ContactType]] = {
    ContactTypeRepository.roach.getEntity(contactTypeId)
  }

  override def deleteEntity(entity: ContactType): Future[Boolean] = {
    ContactTypeRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    ContactTypeRepository.roach.createTable
  }
}


package repository.address.Impl.cockcroachdb

import domain.address.ContactType
import repository.address.ContactTypeRepository


import scala.concurrent.Future

class ContactTypeRepositoryImpl extends ContactTypeRepository{
  override def saveEntity(entity: ContactType): Future[Boolean] = {
    ContactTypeRepository.roach.saveEntity(entity)
  }

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

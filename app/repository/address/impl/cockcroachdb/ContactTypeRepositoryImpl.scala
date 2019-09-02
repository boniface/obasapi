package repository.address.impl.cockcroachdb

import domain.address.ContactType
import repository.address.ContactTypeRepository
import repository.address.impl.cockcroachdb.tables.ContactTypeTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ContactTypeRepositoryImpl extends ContactTypeRepository{
  override def saveEntity(entity: ContactType): Future[Option[ContactType]] = {
    ContactTypeTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[ContactType]] = {
   ContactTypeTable.getEntities
  }

  override def getEntity(contactTypeId: String): Future[Option[ContactType]] = {
   ContactTypeTable.getEntity(contactTypeId)
  }

  override def deleteEntity(entity: ContactType): Future[Boolean] = {
    ContactTypeTable.deleteEntity(entity.contactTypeId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(ContactTypeTable.createTable)
  }
}

package repository.mail.impl.cockcroachdb

import domain.mail.MailApi
import repository.mail.MailApiRepository
import repository.mail.impl.cockcroachdb.tables.MailApiTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class MailApiRepositoryImpl extends MailApiRepository{

  override def saveEntity(entity: MailApi): Future[Option[MailApi]] = {
    MailApiTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[MailApi]] = {
    MailApiTable.getEntities
  }

  override def getEntity(id: String): Future[Option[MailApi]] = {
    MailApiTable.getEntity(id)
  }

  override def deleteEntity(entity: MailApi): Future[Boolean] = {
    MailApiTable.deleteEntity(entity.id)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(MailApiTable.createTable)
  }
}


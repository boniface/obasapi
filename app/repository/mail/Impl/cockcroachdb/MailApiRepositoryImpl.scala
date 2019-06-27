package repository.mail.Impl.cockcroachdb

import domain.mail.MailApi
import repository.mail.Impl.cockcroachdb.tables.MailApiTable
import repository.mail.MailApiRepository

import scala.concurrent.Future

class MailApiRepositoryImpl  extends MailApiRepository{

  override def saveEntity(entity: MailApi): Future[Boolean] = {
    Future.successful(MailApiTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[MailApi]] = {
    MailApiTable.getEntities
  }

  override def getEntity(id: String): Future[Option[MailApi]] = {
    MailApiTable.getEntity(id)
  }

  override def deleteEntity(entity: MailApi): Future[Boolean] = {
    Future.successful(MailApiTable.deleteEntity(entity.id).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(MailApiTable.createTable)
  }
}

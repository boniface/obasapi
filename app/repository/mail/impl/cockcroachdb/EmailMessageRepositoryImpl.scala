package repository.mail.impl.cockcroachdb

import domain.mail.EmailMessage
import repository.mail.EmailMessageRepository
import repository.mail.impl.cockcroachdb.tables.EmailMessageTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class EmailMessageRepositoryImpl extends EmailMessageRepository{
  override def saveEntity(entity: EmailMessage): Future[Option[EmailMessage]] = {
    EmailMessageTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[EmailMessage]] = {
    EmailMessageTable.getEntities
  }

  override def getEntity(email: String): Future[Option[EmailMessage]] = {
    EmailMessageTable.getEntity(email)
  }

  override def deleteEntity(entity: EmailMessage): Future[Boolean] = {
    EmailMessageTable.deleteEntity(entity.email)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(EmailMessageTable.createTable)
  }
}
package services.mail.impl

import domain.mail.EmailMessage
import repository.mail.EmailMessageRepository
import services.mail.EmailMessageService

import scala.concurrent.Future

class EmailMessageServiceImpl extends EmailMessageService {
  override def saveEntity(entity: EmailMessage): Future[Option[EmailMessage]] =
    EmailMessageRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[EmailMessage]] = {
    EmailMessageRepository.roach.getEntities
  }

  override def getEntity(email: String): Future[Option[EmailMessage]] = {
    EmailMessageRepository.roach.getEntity(email)
  }

  override def deleteEntity(entity: EmailMessage): Future[Boolean] = {
    EmailMessageRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    EmailMessageRepository.roach.createTable
  }
}

package services.mail.impl.cockcroachdb

import domain.mail.MailApi
import repository.mail.MailApiRepository
import services.mail.MailApiService

import scala.concurrent.Future

class MailApiServiceImpl extends MailApiService {
  override def saveEntity(entity: MailApi): Future[Boolean] = {
    MailApiRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[MailApi]] = {
    MailApiRepository.roach.getEntities
  }

  override def getEntity(id: String): Future[Option[MailApi]] = {
    MailApiRepository.roach.getEntity(id)
  }

  override def deleteEntity(entity: MailApi): Future[Boolean] = {
    MailApiRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    MailApiRepository.roach.createTable
  }
}


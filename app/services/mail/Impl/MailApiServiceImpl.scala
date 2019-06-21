package services.mail.Impl

import domain.mail.MailApi
import repository.mail.MailApiRepository
import services.mail.MailApiService

import scala.concurrent.Future

class MailApiServiceImpl extends MailApiService {
  override def saveEntity(entity: MailApi): Future[Boolean] = {
    MailApiRepository.apply.saveEntity(entity)
  }

  override def getEntities: Future[Seq[MailApi]] = {
    MailApiRepository.apply.getEntities
  }

  override def getEntity(id: String): Future[Option[MailApi]] = {
    MailApiRepository.apply.getEntity(id)
  }

  override def deleteEntity(entity: MailApi): Future[Boolean] = {
    MailApiRepository.apply.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    MailApiRepository.apply.createTable
  }
}


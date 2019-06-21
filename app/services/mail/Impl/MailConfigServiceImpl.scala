package services.mail.Impl

import domain.mail.MailConfig
import repository.mail.MailConfigRepository
import services.mail.MailConfigService

import scala.concurrent.Future

class MailConfigServiceImpl extends MailConfigService{
  override def saveEntity(entity: MailConfig): Future[Boolean] = {
    MailConfigRepository.apply.saveEntity(entity)
  }

  override def getEntities: Future[Seq[MailConfig]] = {
    MailConfigRepository.apply.getEntities
  }

  override def getEntity(id: String): Future[Option[MailConfig]] = {
    MailConfigRepository.apply.getEntity(id)
  }

  override def deleteEntity(entity: MailConfig): Future[Boolean] = {
    MailConfigRepository.apply.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    MailConfigRepository.apply.createTable
  }

  override def getSiteMailConfigurations(siteId: String): Future[Seq[MailConfig]] = {
    MailConfigRepository.apply.getSiteMailConfigurations(siteId)
  }
}

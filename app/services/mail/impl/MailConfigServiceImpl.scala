package services.mail.impl

import domain.mail.MailConfig
import repository.mail.MailConfigRepository
import services.mail.MailConfigService

import scala.concurrent.Future

class MailConfigServiceImpl extends MailConfigService{
  override def saveEntity(entity: MailConfig): Future[Boolean] = {
    MailConfigRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[MailConfig]] = {
    MailConfigRepository.roach.getEntities
  }

  override def getEntity(id: String): Future[Option[MailConfig]] = {
    MailConfigRepository.roach.getEntity(id)
  }

  override def deleteEntity(entity: MailConfig): Future[Boolean] = {
    MailConfigRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    MailConfigRepository.roach.createTable
  }

  override def getSiteMailConfigurations(siteId: String): Future[Seq[MailConfig]] = {
    MailConfigRepository.roach.getSiteMailConfigurations(siteId)
  }
}

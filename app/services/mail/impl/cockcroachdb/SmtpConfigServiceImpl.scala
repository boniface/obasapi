package services.mail.impl.cockcroachdb

import domain.mail.SmtpConfig
import repository.mail.SmtpConfigRepository
import services.mail.SmtpConfigService

import scala.concurrent.Future

class SmtpConfigServiceImpl extends SmtpConfigService{
  override def saveEntity(entity: SmtpConfig): Future[Boolean] = {
    SmtpConfigRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[SmtpConfig]] = {
    SmtpConfigRepository.roach.getEntities
  }

  override def getEntity(id: String): Future[Option[SmtpConfig]] = {
    SmtpConfigRepository.roach.getEntity(id)
  }

  override def deleteEntity(entity: SmtpConfig): Future[Boolean] = {
    SmtpConfigRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    SmtpConfigRepository.roach.createTable
  }
}

package services.mail.Impl

import domain.mail.SmtpConfig
import repository.mail.SmtpConfigRepository
import services.mail.SmtpConfigService

import scala.concurrent.Future

class SmtpConfigServiceImpl extends SmtpConfigService{
  override def saveEntity(entity: SmtpConfig): Future[Boolean] = {
    SmtpConfigRepository.apply.saveEntity(entity)
  }

  override def getEntities: Future[Seq[SmtpConfig]] = {
    SmtpConfigRepository.apply.getEntities
  }

  override def getEntity(id: String): Future[Option[SmtpConfig]] = {
    SmtpConfigRepository.apply.getEntity(id)
  }

  override def deleteEntity(entity: SmtpConfig): Future[Boolean] = {
    SmtpConfigRepository.apply.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    SmtpConfigRepository.apply.createTable
  }
}

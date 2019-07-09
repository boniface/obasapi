package repository.mail.impl.cockcroachdb

import domain.mail.MailConfig
import repository.mail.impl.cockcroachdb.tables.MailConfigTable
import repository.mail.MailConfigRepository

import scala.concurrent.Future

class MailConfigRepositoryImpl extends MailConfigRepository {

  override def saveEntity(entity: MailConfig): Future[Boolean] = {
    Future.successful(MailConfigTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[MailConfig]] = {
    MailConfigTable.getEntities
  }

  override def getEntity(id: String): Future[Option[MailConfig]] = {
    MailConfigTable.getEntity(id)
  }

  override def deleteEntity(entity: MailConfig): Future[Boolean] = {
    Future.successful(MailConfigTable.deleteEntity(entity.id).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(MailConfigTable.createTable)
  }

  override def getSiteMailConfigurations(siteId: String): Future[Seq[MailConfig]] = ???
}

package repository.mail.impl.cockcroachdb

import domain.mail.SmtpConfig
import repository.mail.impl.cockcroachdb.tables.SmtpConfigTable
import repository.mail.SmtpConfigRepository

import scala.concurrent.Future

class SmtpConfigRepositoryImpl extends SmtpConfigRepository {
  override def saveEntity(entity: SmtpConfig): Future[Boolean] =
    Future.successful(SmtpConfigTable.saveEntity(entity).isCompleted)

  override def getEntities: Future[Seq[SmtpConfig]] =
    SmtpConfigTable.getEntities

  override def getEntity(id: String): Future[Option[SmtpConfig]] =
    SmtpConfigTable.getEntity(id)

  override def deleteEntity(entity: SmtpConfig): Future[Boolean] =
    Future.successful(SmtpConfigTable.deleteEntity(entity.id).isCompleted)

  override def createTable: Future[Boolean] =
    Future.successful(SmtpConfigTable.createTable)
}

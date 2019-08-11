package repository.mail.impl.cockcroachdb

import domain.mail.SmtpConfig
import repository.mail.impl.cockcroachdb.tables.SmtpConfigTable
import repository.mail.SmtpConfigRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class SmtpConfigRepositoryImpl extends SmtpConfigRepository {
  override def saveEntity(entity: SmtpConfig): Future[Option[SmtpConfig]] = {
    SmtpConfigTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[SmtpConfig]] = {
    SmtpConfigTable.getEntities
  }

  override def getEntity(id: String): Future[Option[SmtpConfig]] ={
    SmtpConfigTable.getEntity(id)
  }
   override def deleteEntity(entity: SmtpConfig): Future[Boolean] ={
    SmtpConfigTable.deleteEntity(entity.id)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] =
    Future.successful(SmtpConfigTable.createTable)
}



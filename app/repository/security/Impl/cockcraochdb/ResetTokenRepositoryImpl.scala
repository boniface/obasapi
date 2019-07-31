package repository.security.Impl.cockcraochdb

import domain.security.ResetToken
import repository.security.Impl.cockcraochdb.tables.ResetTokenTable
import repository.security.ResetTokenRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ResetTokenRepositoryImpl extends ResetTokenRepository{

  override def saveEntity(entity: ResetToken): Future[Boolean] = {
    ResetTokenTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[ResetToken]] = {
    ResetTokenTable.getEntities
  }

  override def getEntity(id: String): Future[Option[ResetToken]] = {
    ResetTokenTable.getEntity(id)
  }

  override def deleteEntity(entity: ResetToken): Future[Boolean] = {
    ResetTokenTable.deleteEntity(entity.resetokenvalue)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(ResetTokenTable.createTable)
  }
}

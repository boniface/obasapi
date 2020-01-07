package repository.security.impl.cockroach

import domain.security.ResetToken
import repository.security.ResetTokenRepository
import repository.security.impl.cockroach.tables.ResetTokenTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ResetTokenRepositoryImpl extends ResetTokenRepository{

  override def saveEntity(entity: ResetToken): Future[Option[ResetToken]] = {
    ResetTokenTable.saveEntity(entity)
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

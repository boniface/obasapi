package services.security.Impl

import domain.security.ResetToken
import repository.security.ResetTokenRepository
import services.security.ResetTokenService

import scala.concurrent.Future

class ResetTokenServiceImpl extends ResetTokenService{

  override def saveEntity(entity: ResetToken): Future[Boolean] = {
    ResetTokenRepository.apply.saveEntity(entity)
  }

  override def getEntities: Future[Seq[ResetToken]] = {
    ResetTokenRepository.apply.getEntities
  }

  override def getEntity(id: String): Future[Option[ResetToken]] = {
    ResetTokenRepository.apply.getEntity(id)
  }

  override def deleteEntity(entity: ResetToken): Future[Boolean] = {
    ResetTokenRepository.apply.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    ResetTokenRepository.apply.createTable
  }
}

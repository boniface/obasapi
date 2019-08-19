package services.log.impl.cockroachdb

import domain.log.LogEvent
import repository.log.LogEventRepository
import services.log.LogEventService

import scala.concurrent.Future

class LogEventServiceImpl extends LogEventService{
  override def saveEntity(entity: LogEvent): Future[Option[LogEvent]] = {
    LogEventRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[LogEvent]] = {
    LogEventRepository.roach.getEntities
  }

  override def getEntity(id: String): Future[Option[LogEvent]] =  {
    LogEventRepository.roach.getEntity(id)
  }

  override def deleteEntity(entity: LogEvent): Future[Boolean] = {
    LogEventRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    LogEventRepository.roach.createTable
  }
}

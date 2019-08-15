package repository.log.Impl.cockroachdb

import domain.log.LogEvent
import repository.log.Impl.cockroachdb.tables.LogEventTable
import repository.log.LogEventRepository

import scala.concurrent.Future

class LogEventRepositoryImpl extends LogEventRepository {
  override def saveEntity(entity: LogEvent): Future[Option[LogEvent]] = {
    LogEventTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[LogEvent]] = {
    LogEventTable.getEntities
  }

  override def getEntity(id: String): Future[Option[LogEvent]] = {
    LogEventTable.getEntity(id)
  }

  override def deleteEntity(entity: LogEvent): Future[Boolean] = ??? //{
 //   LogEventTable.deleteEntity(entity.id)map(value=> value.isValidInt)
  //}

  override def createTable: Future[Boolean] = {
    Future.successful(LogEventTable.createTable)
  }
}

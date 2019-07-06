package repository.application.impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.application.ApplicationStatus
import repository.application.ApplicationStatusRepository
import repository.application.impl.cassandra.tables.ApplicationStatusTableImpl
//import util.connections.DataConnection
import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class ApplicationStatusRepositoryImpl extends ApplicationStatusRepository {
  override def saveEntity(entity:ApplicationStatus): Future[Boolean] =
  {
    ApplicationStatusDatabase.ApplicationStatusTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[ApplicationStatus]] = {
    ApplicationStatusDatabase.ApplicationStatusTable.getEntities
  }

  override def getEntity(applicationStatusId: String): Future[Option[ApplicationStatus]] = {
    ApplicationStatusDatabase.ApplicationStatusTable.getEntity(applicationStatusId)
  }

  override def deleteEntity(entity: ApplicationStatus): Future[Boolean] = {
    ApplicationStatusDatabase.ApplicationStatusTable.deleteEntity(entity.applicationStatusId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    ApplicationStatusDatabase.ApplicationStatusTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}

class ApplicationStatusDatabase(override  val connector: KeySpaceDef) extends Database[ApplicationStatusDatabase](connector)
{
  object ApplicationStatusTable extends ApplicationStatusTableImpl with connector.Connector
}

object ApplicationStatusDatabase extends ApplicationStatusDatabase(DataConnection.connector)

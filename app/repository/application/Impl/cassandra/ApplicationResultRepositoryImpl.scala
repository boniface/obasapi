package repository.application.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.application.ApplicationResult
import repository.application.ApplicationResultRepository
import repository.application.Impl.cassandra.tables.ApplicationResultTableImpl
import util.connections.DataConnection

import scala.concurrent.Future


class ApplicationResultRepositoryImpl extends ApplicationResultRepository{
  override def saveEntity(entity: ApplicationResult): Future[Boolean] = {
    ApplicationResultDatabase.ApplicationResultTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[ApplicationResult]] = {
    ApplicationResultDatabase.ApplicationResultTable.getEntities
  }

  override def getEntity(applicationResultId: String): Future[Option[ApplicationResult]] ={
    ApplicationResultDatabase.ApplicationResultTable.getEntity(applicationResultId)
  }

  override def deleteEntity(entity: ApplicationResult): Future[Boolean] = {
    ApplicationResultDatabase.ApplicationResultTable.deleteEntity(entity.applicationResultId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {

    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    ApplicationResultDatabase.ApplicationResultTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}

class ApplicationResultDatabase(override val connector: KeySpaceDef)extends Database[ApplicantTypeDatabase](connector){
  object ApplicationResultTable extends ApplicationResultTableImpl with connector.Connector
}

object ApplicationResultDatabase extends ApplicationResultDatabase(DataConnection.connector)
package repository.institutions.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.institutions.School
import repository.institutions.Impl.cassandra.tables.SchoolTableImpl
import repository.institutions.SchoolRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class SchoolRepositoryImpl extends SchoolRepository{
  override def saveEntity(entity: School): Future[Boolean] = {
    SchoolDatabase.schoolTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[School]] = {
    SchoolDatabase.schoolTable.getEntities
  }

  override def getEntity(schoolId: String): Future[Option[School]] = {
    SchoolDatabase.schoolTable.getEntity(schoolId)
  }

  override def deleteEntity(entity: School): Future[Boolean] = {
    SchoolDatabase.schoolTable.deleteEntity(entity.schoolId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    SchoolDatabase.schoolTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}
class SchoolDatabase(override val connector: KeySpaceDef) extends Database[SchoolDatabase](connector) {
  object schoolTable extends SchoolTableImpl with connector.Connector

}

object SchoolDatabase extends SchoolDatabase(DataConnection.connector)
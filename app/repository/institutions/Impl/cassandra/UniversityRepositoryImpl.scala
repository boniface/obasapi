package repository.institutions.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.institutions.University
import repository.institutions.Impl.cassandra.tables.UniversityTableImpl
import repository.institutions.UniversityRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class UniversityRepositoryImpl extends UniversityRepository{
  override def saveEntity(entity: University): Future[Boolean] = {
    UniversityDatabase.universityTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[University]] = {
    UniversityDatabase.universityTable.getEntities
  }

  override def getEntity(universityId: String): Future[Option[University]] = {
    UniversityDatabase.universityTable.getEntity(universityId)
  }

  override def deleteEntity(entity: University): Future[Boolean] = {
    UniversityDatabase.universityTable.deleteEntity(entity.universityId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UniversityDatabase.universityTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}
class UniversityDatabase(override val connector: KeySpaceDef) extends Database[UniversityDatabase](connector) {
  object universityTable extends UniversityTableImpl with connector.Connector

}

object UniversityDatabase extends UniversityDatabase(DataConnection.connector)
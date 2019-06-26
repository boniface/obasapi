package repository.demographics.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.demographics.Race
import repository.demographics.Impl.cassandra.tables.RaceTableImpl
import repository.demographics.RaceRepository
import util.connections.DataConnection

import scala.concurrent.Future

class RaceRepositoryImpl extends RaceRepository{
  override def saveEntity(entity: Race): Future[Boolean] = {
    RaceDatabase.RaceTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[Race]] = {
    RaceDatabase.RaceTable.getEntities
  }

  override def getEntity(raceId: String): Future[Option[Race]] = {
    RaceDatabase.RaceTable.getEntity(raceId)
  }

  override def deleteEntity(entity: Race): Future[Boolean] = {
    RaceDatabase.RaceTable.deleteEntity(entity.raceId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    RaceDatabase.RaceTable.create.ifNotExists().future().map(result => result.head.isExhausted())
  }
}
 class RaceDatabase(override val connector: KeySpaceDef) extends Database[RaceDatabase](connector){
   object RaceTable extends RaceTableImpl with connector.Connector
 }

object RaceDatabase extends RaceDatabase(DataConnection.connector)
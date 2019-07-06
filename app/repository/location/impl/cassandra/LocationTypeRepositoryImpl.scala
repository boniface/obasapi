package repository.location.impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.location.LocationType
import repository.location.impl.cassandra.tables.LocationTypeTableImpl
import repository.location.LocationTypeRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class LocationTypeRepositoryImpl extends LocationTypeRepository{
  override def saveEntity(entity: LocationType): Future[Boolean] = {
    LocationTypeDatabase.locationTypeTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[LocationType]] = {
    LocationTypeDatabase.locationTypeTable.getEntities
  }

  override def getEntity(locationTypeId: String): Future[Option[LocationType]] = {
    LocationTypeDatabase.locationTypeTable.getEntity(locationTypeId)
  }

  override def deleteEntity(entity: LocationType): Future[Boolean] = {
    LocationTypeDatabase.locationTypeTable.deleteEntity(entity.locationTypeId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    LocationTypeDatabase.locationTypeTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}
class LocationTypeDatabase(override val connector: KeySpaceDef) extends Database[LocationTypeDatabase](connector) {
  object locationTypeTable extends LocationTypeTableImpl with connector.Connector

}

object LocationTypeDatabase extends LocationTypeDatabase(DataConnection.connector)
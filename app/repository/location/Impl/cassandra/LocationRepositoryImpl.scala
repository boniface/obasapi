package repository.location.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.location.Location
import repository.location.Impl.cassandra.tables.LocationTableImpl
import repository.location.LocationRepository
import util.connections.DataConnection

import scala.concurrent.Future

class LocationRepositoryImpl extends LocationRepository{
  override def saveEntity(entity: Location): Future[Boolean] = {
    LocationDatabase.locationTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[Location]] = {
    LocationDatabase.locationTable.getEntities
  }

  override def getEntity(locationId: String): Future[Option[Location]] = {
    LocationDatabase.locationTable.getEntity(locationId)
  }

  override def deleteEntity(entity: Location): Future[Boolean] = {
    LocationDatabase.locationTable.deleteEntity(entity.locationId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    LocationDatabase.locationTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}
class LocationDatabase(override val connector: KeySpaceDef) extends Database[LocationDatabase](connector) {
  object locationTable extends LocationTableImpl with connector.Connector

}

object LocationDatabase extends LocationDatabase(DataConnection.connector)
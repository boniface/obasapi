package repository.location.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.location.LocationType

import scala.concurrent.Future

abstract class LocationTypeTable extends Table[LocationTypeTable, LocationType] {

  object locationTypeId extends StringColumn with PartitionKey

  object name extends StringColumn

}

abstract class LocationTypeTableImpl extends LocationTypeTable with RootConnector {

  override lazy val tableName = "locationType"

  def saveEntity(entity: LocationType): Future[ResultSet] = {
    insert
      .value(_.locationTypeId, entity.locationTypeId)
      .value(_.name, entity.name)
      .future()
  }

  def getEntity(locationTypeId: String): Future[Option[LocationType]] = {
    select
      .where(_.locationTypeId eqs locationTypeId)
      .one()
  }

  def getEntities: Future[Seq[LocationType]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(locationTypeId: String): Future[ResultSet] = {
    delete
      .where(_.locationTypeId eqs locationTypeId)
      .future()
  }
}
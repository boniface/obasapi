package repository.location.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.location.Location

import scala.concurrent.Future

abstract class LocationTable extends Table[LocationTable, Location] {

  object locationId extends StringColumn with PartitionKey

  object name extends StringColumn

  object latitude extends StringColumn

  object longitude extends StringColumn

  object locationTypeId extends StringColumn

  object  locationParentId extends StringColumn

}


abstract class LocationTableImpl extends LocationTable with RootConnector {

  override lazy val tableName = "location"

  def saveEntity(entity: Location): Future[ResultSet] = {
    insert
      .value(_.locationId, entity.locationId)
      .value(_.name, entity.name)
      .value(_.locationParentId, entity.locationParentId)
      .value(_.latitude, entity.latitude)
      .value(_.longitude, entity.longitude)
      .value(_.locationTypeId, entity.locationTypeId)
      .future()
  }


  def getEntity(locationId: String): Future[Option[Location]] = {
    select
      .where(_.locationId eqs locationId)
      .one()
  }

  def getEntities: Future[Seq[Location]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(locationId: String): Future[ResultSet] = {
    delete
      .where(_.locationId eqs locationId)
      .future()
  }
}
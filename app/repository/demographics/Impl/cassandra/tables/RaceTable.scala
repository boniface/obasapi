package repository.demographics.Impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.demographics.Race

import scala.concurrent.Future


abstract class RaceTable extends Table[RaceTable,Race]{

  object raceId extends StringColumn with PartitionKey

  object raceName extends StringColumn
  
  
}

abstract class RaceTableImpl extends RaceTable with RootConnector{

  override  lazy val tableName ="Race"


  def saveEntity(entity:Race): Future[ResultSet] ={
    insert
      .value(_.raceId, entity.raceId)
      .value(_.raceName, entity.raceName)
      .future()

  }

  def getEntity(raceId: String): Future[Option[Race]] = {
    select
      .where(_.raceId eqs raceId)
      .one()
  }

  def getEntities: Future[Seq[Race]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(raceId: String): Future[ResultSet] = {
    delete
      .where(_.raceId eqs raceId)
      .future()
  }
  
  
}

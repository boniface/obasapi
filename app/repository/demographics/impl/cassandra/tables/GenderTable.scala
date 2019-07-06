package repository.demographics.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.demographics.Gender

import scala.concurrent.Future

abstract class GenderTable extends Table[GenderTable, Gender] {

  object genderId extends StringColumn with PartitionKey

  object genderName extends StringColumn
  
}

abstract class GenderTableImpl extends GenderTable with RootConnector{

  override  lazy val tableName ="Gender"


  def saveEntity(entity:Gender): Future[ResultSet] ={
    insert
      .value(_.genderId, entity.genderId)
      .value(_.genderName, entity.genderName)
      .future()

  }

  def getEntity(genderId: String): Future[Option[Gender]] = {
    select
      .where(_.genderId eqs genderId)
      .one()
  }

  def getEntities: Future[Seq[Gender]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(genderId: String): Future[ResultSet] = {
    delete
      .where(_.genderId eqs genderId)
      .future()
  }


}

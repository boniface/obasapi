package repository.demographics.Impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.demographics.Roles

import scala.concurrent.Future


abstract class RolesTable extends Table[RolesTable, Roles]{

  object id extends StringColumn with PartitionKey

  object roleName extends StringColumn
  
}

abstract class RolesTableImpl extends RolesTable with RootConnector{
  override  lazy val tableName ="Roles"


  def saveEntity(entity:Roles): Future[ResultSet] ={
    insert
      .value(_.id, entity.id)
      .value(_.roleName, entity.roleName)
      .future()

  }

  def getEntity(id: String): Future[Option[Roles]] = {
    select
      .where(_.id eqs id)
      .one()
  }

  def getEntities: Future[Seq[Roles]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(id: String): Future[ResultSet] = {
    delete
      .where(_.id eqs id)
      .future()
  }


}

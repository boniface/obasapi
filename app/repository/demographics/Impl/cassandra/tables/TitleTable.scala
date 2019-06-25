package repository.demographics.Impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.demographics.Title

import scala.concurrent.Future


abstract class TitleTable extends Table[TitleTable,Title]{

  object titleId extends StringColumn with PartitionKey

  object titlename extends StringColumn
  
}

abstract class TitleTableImpl extends TitleTable with RootConnector{

  override  lazy val tableName ="Title"


  def saveEntity(entity:Title): Future[ResultSet] ={
    insert
      .value(_.titleId, entity.titleId)
      .value(_.titlename, entity.titlename)
      .future()

  }

  def getEntity(titleId: String): Future[Option[Title]] = {
    select
      .where(_.titleId eqs titleId)
      .one()
  }

  def getEntities: Future[Seq[Title]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(titleId: String): Future[ResultSet] = {
    delete
      .where(_.titleId eqs titleId)
      .future()
  }


}

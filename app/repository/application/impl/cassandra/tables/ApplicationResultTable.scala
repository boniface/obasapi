package repository.application.impl.cassandra.tables

import java.time.LocalDateTime

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import com.outworkers.phantom.jdk8._
import domain.application.ApplicationResult

import scala.concurrent.Future

abstract class ApplicationResultTable extends Table[ApplicationResultTable,ApplicationResult]{

  object applicationResultId extends StringColumn with PartitionKey

  object description extends StringColumn

  object date extends Col[LocalDateTime]

}

abstract class ApplicationResultTableImpl extends ApplicationResultTable with RootConnector{

  override  lazy val tableName ="ApplicationResult"


  def saveEntity(entity:ApplicationResult): Future[ResultSet] ={
    insert
      .value(_.applicationResultId, entity.applicationResultId)
      .value(_.description, entity.description)
      .value(_.date, entity.date)
      .future()

  }

  def getEntity(applicationResultId: String): Future[Option[ApplicationResult]] = {
    select
      .where(_.applicationResultId eqs applicationResultId)
      .one()
  }

  def getEntities: Future[Seq[ApplicationResult]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(applicationResultId: String): Future[ResultSet] = {
    delete
      .where(_.applicationResultId eqs applicationResultId)
      .future()
  }


}

package repository.application.impl.cassandra.tables

import java.time.LocalDateTime

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import com.outworkers.phantom.jdk8._
import domain.application.ApplicationStatus

import scala.concurrent.Future


abstract class ApplicationStatusTable extends Table[ApplicationStatusTable,ApplicationStatus]{

  object applicationStatusId extends StringColumn with PartitionKey

  object description extends StringColumn

  object date extends Col[LocalDateTime]

}

abstract class ApplicationStatusTableImpl extends ApplicationStatusTable with RootConnector{

  override  lazy val tableName ="ApplicationStatus"


  def saveEntity(entity:ApplicationStatus): Future[ResultSet] ={
    insert
      .value(_.applicationStatusId, entity.applicationStatusId)
      .value(_.description, entity.description)
      .value(_.date, entity.date)
      .future()

  }

  def getEntity(applicationStatusId: String): Future[Option[ApplicationStatus]] = {
    select
      .where(_.applicationStatusId eqs applicationStatusId)
      .one()
  }

  def getEntities: Future[Seq[ApplicationStatus]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(applicationStatusId: String): Future[ResultSet] = {
    delete
      .where(_.applicationStatusId eqs applicationStatusId)
      .future()
  }


}
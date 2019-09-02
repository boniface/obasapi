package repository.login.impl.cassandra.tables

import java.time.LocalDateTime

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.jdk8._
import com.outworkers.phantom.streams._
import domain.login.UserLoginEvents

import scala.concurrent.Future

abstract class UserLoginEventsTable extends Table[UserLoginEventsTable, UserLoginEvents] with RootConnector {

  object email extends StringColumn with PartitionKey

  object id extends StringColumn with PrimaryKey

  object date extends Col[LocalDateTime]

  object description extends StringColumn


  override lazy val tableName = "loginevents"

  def saveEntity(entity: UserLoginEvents): Future[ResultSet] = {
    insert
      .value(_.email, entity.email)
      .value(_.id, entity.id)
      .value(_.date, entity.date)
      .value(_.description, entity.description)
      .ttl( 86400)
      .future()
  }
  def getEntities: Future[Seq[UserLoginEvents]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }


  def getEntity(email: String, id:String): Future[Option[UserLoginEvents]] = {
    select
      .where(_.email eqs email)
      .and(_.id eqs id)
      .one()
  }

  def getUserLoginEvents(email: String): Future[Seq[UserLoginEvents]] = {
    select
      .where(_.email eqs email)
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(entity: UserLoginEvents): Future[ResultSet] = {
    delete
      .where(_.email eqs entity.email)
      .and(_.id eqs entity.id)
      .future()
  }
}
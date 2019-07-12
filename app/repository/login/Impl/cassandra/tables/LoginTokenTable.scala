package repository.login.Impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.login.LoginToken

import scala.concurrent.Future

abstract class LoginTokenTable extends Table[LoginTokenTable, LoginToken] with RootConnector {

  object email extends StringColumn with PartitionKey

  object token extends StringColumn

  override lazy val tableName = "logintoken"

  def saveEntity(entity: LoginToken): Future[ResultSet] = {
    insert
      .value(_.email, entity.email)
      .value(_.token, entity.token)
      .ttl( 86400)
      .future()
  }

  def getEntity(email: String): Future[Option[LoginToken]] = {
    select
      .where(_.email eqs email)
      .one()
  }

  def getEntities: Future[Seq[LoginToken]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(email: String): Future[ResultSet] = {
    delete
      .where(_.email eqs email)
      .future()
  }
}
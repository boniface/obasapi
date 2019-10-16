package repository.users.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.UserApplication

import scala.concurrent.Future

abstract class UserApplicationResultTable extends Table[UserApplicationResultTable, UserApplication] {

  object userApplicationResultId extends StringColumn with PartitionKey

  object description extends StringColumn

}

abstract class UserApplicationResultTableImpl extends UserApplicationResultTable with RootConnector {

  override lazy val tableName = "userApplicationResult"

  def saveEntity(entity: UserApplication): Future[ResultSet] = {
    insert
      .value(_.userApplicationResultId, entity.userApplicationResultId)
      .value(_.description, entity.description)
      .future()
  }

  def getEntity(userApplicationResultId: String): Future[Option[UserApplication]] = {
    select
      .where(_.userApplicationResultId eqs userApplicationResultId)
      .one()
  }

  def getEntities: Future[Seq[UserApplication]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(userApplicationResultId: String): Future[ResultSet] = {
    delete
      .where(_.userApplicationResultId eqs userApplicationResultId)
      .future()
  }
}
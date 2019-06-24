package repository.users.Impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.UserApplicationResult

import scala.concurrent.Future

abstract class UserApplicationResultTable extends Table[UserApplicationResultTable, UserApplicationResult] {

  object userApplicationResultId extends StringColumn with PartitionKey

  object description extends StringColumn

}

abstract class UserApplicationResultTableImpl extends UserApplicationResultTable with RootConnector {

  override lazy val tableName = "userApplicationResult"

  def saveEntity(entity: UserApplicationResult): Future[ResultSet] = {
    insert
      .value(_.userApplicationResultId, entity.userApplicationResultId)
      .value(_.description, entity.description)
      .future()
  }

  def getEntity(userApplicationResultId: String): Future[Option[UserApplicationResult]] = {
    select
      .where(_.userApplicationResultId eqs userApplicationResultId)
      .one()
  }

  def getEntities: Future[Seq[UserApplicationResult]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(userApplicationResultId: String): Future[ResultSet] = {
    delete
      .where(_.userApplicationResultId eqs userApplicationResultId)
      .future()
  }
}
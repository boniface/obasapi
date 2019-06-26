package repository.users.Impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.UserResults

import scala.concurrent.Future

abstract class UserResultsTable extends Table[UserResultsTable, UserResults] {

  object userResultsId extends StringColumn with PartitionKey

  object description extends StringColumn

}

abstract class UserResultsTableImpl extends UserResultsTable with RootConnector {

  override lazy val tableName = "userResults"

  def saveEntity(entity: UserResults): Future[ResultSet] = {
    insert
      .value(_.userResultsId, entity.userResultsId)
      .value(_.description, entity.description)
      .future()
  }

  def getEntity(userResultsId: String): Future[Option[UserResults]] = {
    select
      .where(_.userResultsId eqs userResultsId)
      .one()
  }

  def getEntities: Future[Seq[UserResults]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(userResultsId: String): Future[ResultSet] = {
    delete
      .where(_.userResultsId eqs userResultsId)
      .future()
  }
}

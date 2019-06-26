package repository.users.Impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.UserCommunication

import scala.concurrent.Future

abstract class UserCommunicationTable extends Table[UserCommunicationTable, UserCommunication] {

  object communicationId extends StringColumn with PartitionKey

  object description extends StringColumn

}

abstract class UserCommunicationTableImpl extends UserCommunicationTable with RootConnector {

  override lazy val tableName = "userCommunication"

  def saveEntity(entity: UserCommunication): Future[ResultSet] = {
    insert
      .value(_.communicationId, entity.communicationId)
      .value(_.description, entity.description)
      .future()
  }

  def getEntity(communicationId: String): Future[Option[UserCommunication]] = {
    select
      .where(_.communicationId eqs communicationId)
      .one()
  }

  def getEntities: Future[Seq[UserCommunication]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(communicationId: String): Future[ResultSet] = {
    delete
      .where(_.communicationId eqs communicationId)
      .future()
  }
}
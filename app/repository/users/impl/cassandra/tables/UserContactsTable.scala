package repository.users.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.UserContacts

import scala.concurrent.Future

abstract class UserContactsTable extends Table[UserContactsTable, UserContacts] {

  object userContactId extends StringColumn with PartitionKey

  object cellNumber extends StringColumn

  object alternativeNumber extends StringColumn

  object alternativeEmail extends StringColumn

}

abstract class UserContactsTableImpl extends UserContactsTable with RootConnector {

  override lazy val tableName = "userContacts"

  def saveEntity(entity: UserContacts): Future[ResultSet] = ???

  def getEntity(userContactId: String): Future[Option[UserContacts]] = {
    select
      .where(_.userContactId eqs userContactId)
      .one()
  }

  def getEntities: Future[Seq[UserContacts]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(userContactId: String): Future[ResultSet] = {
    delete
      .where(_.userContactId eqs userContactId)
      .future()
  }
}

package repository.users.Impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.UserPassword

import scala.concurrent.Future

abstract class UserPasswordTable extends Table[UserPasswordTable, UserPassword] {

  object userPasswordId extends StringColumn with PartitionKey

  object password extends StringColumn

}

abstract class UserPasswordTableImpl extends UserPasswordTable with RootConnector {

  override lazy val tableName = "userPassword"

  def saveEntity(entity: UserPassword): Future[ResultSet] = {
    insert
      .value(_.userPasswordId, entity.userPasswordId)
      .value(_.password, entity.password)
      .future()
  }

  def getEntity(userPasswordId: String): Future[Option[UserPassword]] = {
    select
      .where(_.userPasswordId eqs userPasswordId)
      .one()
  }

  def getEntities: Future[Seq[UserPassword]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(userPasswordId: String): Future[ResultSet] = {
    delete
      .where(_.userPasswordId eqs userPasswordId)
      .future()
  }
}

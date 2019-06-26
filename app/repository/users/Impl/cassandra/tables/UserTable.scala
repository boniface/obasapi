package repository.users.Impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.User

import scala.concurrent.Future

abstract class UserTable extends Table[UserTable, User] {

  object email extends StringColumn with PartitionKey

  object firstName extends StringColumn

  object middleName extends StringColumn

  object lastName extends StringColumn

  object dateOfBirth extends StringColumn

}

abstract class UserTableImpl extends UserTable with RootConnector {

  override lazy val tableName = "user"

  def saveEntity(entity: User): Future[ResultSet] = {
    insert
      .value(_.email, entity.email)
      .value(_.firstName, entity.firstName)
      .value(_.middleName, entity.middleName)
      .value(_.lastName, entity.lastName)
      .value(_.dateOfBirth, entity.dateOfBirth)
      .future()
  }

  def getEntity(email: String): Future[Option[User]] = {
    select
      .where(_.email eqs email)
      .one()
  }

  def getEntities: Future[Seq[User]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(email: String): Future[ResultSet] = {
    delete
      .where(_.email eqs email)
      .future()
  }
}
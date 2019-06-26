package repository.users.Impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.UserRole

import scala.concurrent.Future

abstract class UserRoleTable extends Table[UserRoleTable, UserRole] {

  object userRoleId extends StringColumn with PartitionKey

  object name extends StringColumn

  object description extends StringColumn

}

abstract class UserRoleTableImpl extends UserRoleTable with RootConnector {

  override lazy val tableName = "userRole"

  def saveEntity(entity: UserRole): Future[ResultSet] = {
    insert
      .value(_.userRoleId, entity.userRoleId)
      .value(_.name, entity.name)
      .value(_.description, entity.description)
      .future()
  }

  def getEntity(userRoleId: String): Future[Option[UserRole]] = {
    select
      .where(_.userRoleId eqs userRoleId)
      .one()
  }

  def getEntities: Future[Seq[UserRole]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(userRoleId: String): Future[ResultSet] = {
    delete
      .where(_.userRoleId eqs userRoleId)
      .future()
  }
}
package repository.users.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.UserRole

import scala.concurrent.Future

abstract class UserRoleTable extends Table[UserRoleTable, UserRole] {

  object userId extends StringColumn with PartitionKey

  object name extends StringColumn

  object description extends StringColumn

}

abstract class UserRoleTableImpl extends UserRoleTable with RootConnector {

  override lazy val tableName = "userRole"

  def saveEntity(entity: UserRole): Future[ResultSet] = {
    insert
      .value(_.userId, entity.userId)
      .value(_.name, entity.roleId)
      .future()
  }

  def getEntity(userId: String): Future[Option[UserRole]] = {
    select
      .where(_.userId eqs userId)
      .one()
  }

  def getEntities: Future[Seq[UserRole]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(userId: String): Future[ResultSet] = {
    delete
      .where(_.userId eqs userId)
      .future()
  }
}
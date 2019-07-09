package repository.users.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.UserRelative

import scala.concurrent.Future

abstract class UserRelativeTable extends Table[UserRelativeTable, UserRelative] {

  object userRelativeId extends StringColumn with PartitionKey

  object name extends StringColumn

  object cellphone extends StringColumn

  object relationship extends StringColumn

  object email extends StringColumn

}

abstract class UserRelativeTableImpl extends UserRelativeTable with RootConnector {

  override lazy val tableName = "userRelative"

  def saveEntity(entity: UserRelative): Future[ResultSet] = {
    insert
      .value(_.userRelativeId, entity.userRelativeId)
      .value(_.name, entity.name)
      .value(_.cellphone, entity.cellphone)
      .value(_.relationship, entity.relationship)
      .value(_.email, entity.email)
      .future()
  }

  def getEntity(userRelativeId: String): Future[Option[UserRelative]] = {
    select
      .where(_.userRelativeId eqs userRelativeId)
      .one()
  }

  def getEntities: Future[Seq[UserRelative]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(userRelativeId: String): Future[ResultSet] = {
    delete
      .where(_.userRelativeId eqs userRelativeId)
      .future()
  }
}
package repository.users.Impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.UserSubjects

import scala.concurrent.Future

abstract class UserSubjectsTable extends Table[UserSubjectsTable, UserSubjects] {

  object userSubjectId extends StringColumn with PartitionKey

  object name extends StringColumn

  object description extends StringColumn

  object term extends StringColumn

}

abstract class UserSubjectsTableImpl extends UserSubjectsTable with RootConnector {

  override lazy val tableName = "userSubjects"

  def saveEntity(entity: UserSubjects): Future[ResultSet] = {
    insert
      .value(_.userSubjectId, entity.userSubjectId)
      .value(_.name, entity.name)
      .value(_.description, entity.description)
      .value(_.term, entity.term)
      .future()
  }

  def getEntity(userSubjectId: String): Future[Option[UserSubjects]] = {
    select
      .where(_.userSubjectId eqs userSubjectId)
      .one()
  }

  def getEntities: Future[Seq[UserSubjects]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(userSubjectId: String): Future[ResultSet] = {
    delete
      .where(_.userSubjectId eqs userSubjectId)
      .future()
  }
}
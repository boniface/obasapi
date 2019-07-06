package repository.institutions.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.institutions.School

import scala.concurrent.Future

abstract class SchoolTable extends Table[SchoolTable, School] {

  object schoolId extends StringColumn with PartitionKey

  object schoolName extends StringColumn

  object schoolDetails extends StringColumn

  object schoolState extends StringColumn

}

abstract class SchoolTableImpl extends SchoolTable with RootConnector {

  override lazy val tableName = "school"

  def saveEntity(entity: School): Future[ResultSet] = {
    insert
      .value(_.schoolId, entity.schoolId)
      .value(_.schoolName, entity.schoolName)
      .value(_.schoolDetails, entity.schoolDetails)
      .value(_.schoolState, entity.schoolState)
      .future()
  }

  def getEntity(schoolId: String): Future[Option[School]] = {
    select
      .where(_.schoolId eqs schoolId)
      .one()
  }

  def getEntities: Future[Seq[School]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(schoolId: String): Future[ResultSet] = {
    delete
      .where(_.schoolId eqs schoolId)
      .future()
  }
}
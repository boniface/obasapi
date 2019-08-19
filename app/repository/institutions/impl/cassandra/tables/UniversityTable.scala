package repository.institutions.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.institutions.University

import scala.concurrent.Future

abstract class UniversityTable extends Table[UniversityTable, University] {

  object universityId extends StringColumn with PartitionKey

  object universityName extends StringColumn

  object universityProvince extends StringColumn

  object universityPhoneNumber extends StringColumn

  object universityEmail extends StringColumn

}

abstract class UniversityTableImpl extends UniversityTable with RootConnector {

  override lazy val tableName = "university"

  def saveEntity(entity: University): Future[ResultSet] = {
    insert
      .value(_.universityId, entity.universityId)
      .value(_.universityName, entity.universityName)
      .value(_.universityProvince, entity.universityProvince)
      .value(_.universityPhoneNumber, entity.universityPhoneNumber)
      .value(_.universityEmail, entity.universityEmail)
      .future()
  }

  def getEntity(universityId: String): Future[Option[University]] = {
    select
      .where(_.universityId eqs universityId)
      .one()
  }

  def getEntities: Future[Seq[University]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(universityId: String): Future[ResultSet] = {
    delete
      .where(_.universityId eqs universityId)
      .future()
  }
}
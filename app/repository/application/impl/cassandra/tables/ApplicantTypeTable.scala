package repository.application.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.application.ApplicantType

//.address.ApplicantType

import scala.concurrent.Future

abstract class ApplicantTypeTable extends Table[ApplicantTypeTable ,ApplicantType]{

  object applicantTypeId extends StringColumn with PartitionKey

  object name extends StringColumn


}

abstract class ApplicantTypeTableImpl extends ApplicantTypeTable with RootConnector{

  override  lazy val tableName ="ApplicantType"


  def saveEntity(entity:ApplicantType): Future[ResultSet] ={
    insert
      .value(_.applicantTypeId, entity.applicantTypeId)
      .value(_.name, entity.name)
      .future()

  }

  def getEntity(applicantTypeId: String): Future[Option[ApplicantType]] = {
    select
      .where(_.applicantTypeId eqs applicantTypeId)
      .one()
  }

  def getEntities: Future[Seq[ApplicantType]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(applicantTypeId: String): Future[ResultSet] = {
    delete
      .where(_.applicantTypeId eqs applicantTypeId)
      .future()
  }


}
package repository.users.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.UserDemographics

import scala.concurrent.Future

abstract class UserDemographicsTable extends Table[UserDemographicsTable, UserDemographics] {

  object userDemographicsId extends StringColumn with PartitionKey

  object genderId extends StringColumn

  object raceId extends StringColumn

}

abstract class UserDemographicsTableImpl extends UserDemographicsTable with RootConnector {

  override lazy val tableName = "userDemographics"

  def saveEntity(entity: UserDemographics): Future[ResultSet] = {
    insert
      .value(_.userDemographicsId, entity.userId)
      .value(_.genderId, entity.genderId)
      .value(_.raceId, entity.raceId)
      .future()
  }

  def getEntity(userDemographicsId: String): Future[Option[UserDemographics]] = {
    select
      .where(_.userDemographicsId eqs userDemographicsId)
      .one()
  }

  def getEntities: Future[Seq[UserDemographics]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(userDemographicsId: String): Future[ResultSet] = {
    delete
      .where(_.userDemographicsId eqs userDemographicsId)
      .future()
  }
}
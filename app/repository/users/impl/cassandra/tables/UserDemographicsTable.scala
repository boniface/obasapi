package repository.users.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.UserDemographics

import scala.concurrent.Future

abstract class UserDemographicsTable extends Table[UserDemographicsTable, UserDemographics] {

  object userId extends StringColumn with PartitionKey

  object genderId extends StringColumn

  object raceId extends StringColumn

  object titleId extends StringColumn

}

abstract class UserDemographicsTableImpl extends UserDemographicsTable with RootConnector {

  override lazy val tableName = "userDemographics"

  def saveEntity(entity: UserDemographics): Future[ResultSet] = {
    insert
      .value(_.userId, entity.userId)
      .value(_.genderId, entity.genderId)
      .value(_.raceId, entity.raceId)
      .value(_.titleId, entity.titleId)
      .future()
  }

  def getEntity(userId: String): Future[Option[UserDemographics]] = {
    select
      .where(_.userId eqs userId)
      .one()
  }

  def getEntities: Future[Seq[UserDemographics]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(userId: String): Future[ResultSet] = {
    delete
      .where(_.userId eqs userId)
      .future()
  }
}
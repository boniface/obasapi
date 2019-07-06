package repository.users.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.UserInstitution

import scala.concurrent.Future

abstract class UserInstitutionTable extends Table[UserInstitutionTable, UserInstitution] {

  object userInstitutionId extends StringColumn with PartitionKey

  object name extends StringColumn

}

abstract class UserInstitutionTableImpl extends UserInstitutionTable with RootConnector {

  override lazy val tableName = "userInstitution"

  def saveEntity(entity: UserInstitution): Future[ResultSet] = {
    insert
      .value(_.userInstitutionId, entity.userInstitutionId)
      .value(_.name, entity.name)
      .future()
  }

  def getEntity(userInstitutionId: String): Future[Option[UserInstitution]] = {
    select
      .where(_.userInstitutionId eqs userInstitutionId)
      .one()
  }

  def getEntities: Future[Seq[UserInstitution]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(userInstitutionId: String): Future[ResultSet] = {
    delete
      .where(_.userInstitutionId eqs userInstitutionId)
      .future()
  }
}
package repository.users.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.users.UserAddress

import scala.concurrent.Future

abstract class UserAddressTable extends Table[UserAddressTable, UserAddress] {

  object userAddressId extends StringColumn with PartitionKey

  object physicalAddress extends StringColumn

  object postalCode extends StringColumn


}

abstract class UserAddressTableImpl extends UserAddressTable with RootConnector {

  override lazy val tableName = "userAddress"

  def saveEntity(entity: UserAddress): Future[ResultSet] = {
    insert
      .value(_.userAddressId, entity.userAddressId)
      .value(_.physicalAddress, entity.physicalAddress)
      .value(_.postalCode, entity.postalCode)
      .future()
  }

  def getEntity(userAddressId: String): Future[Option[UserAddress]] = {
    select
      .where(_.userAddressId eqs userAddressId)
      .one()
  }

  def getEntities: Future[Seq[UserAddress]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(userAddressId: String): Future[ResultSet] = {
    delete
      .where(_.userAddressId eqs userAddressId)
      .future()
  }
}
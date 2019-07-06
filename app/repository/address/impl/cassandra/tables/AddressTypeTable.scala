package repository.address.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.address.AddressType

import scala.concurrent.Future

abstract class AddressTypeTable extends Table[AddressTypeTable ,AddressType]{

  object addressTypeID extends StringColumn with PartitionKey

  object addressName extends StringColumn

}

abstract class AddressTypeTableImpl extends AddressTypeTable with RootConnector{

  override  lazy val tableName ="AddressType"


  def saveEntity(entity:AddressType): Future[ResultSet] ={
    insert
      .value(_.addressTypeID, entity.addressTypeID)
      .value(_.addressName, entity.addressName)
      .future()

  }

  def getEntity(addressTypeID: String): Future[Option[AddressType]] = {
    select
      .where(_.addressTypeID eqs addressTypeID)
      .one()
  }

  def getEntities: Future[Seq[AddressType]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(addressTypeID: String): Future[ResultSet] = {
    delete
      .where(_.addressTypeID eqs addressTypeID)
      .future()
  }


}

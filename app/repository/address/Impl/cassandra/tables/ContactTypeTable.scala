package repository.address.Impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.address.ContactType

import scala.concurrent.Future

abstract class ContactTypeTable extends Table[ContactTypeTable, ContactType]{

  object ContactType extends StringColumn with PartitionKey

  object name extends StringColumn



}

abstract class ContactTypeTableImpl extends ContactTypeTable with RootConnector{

    override lazy val tableName ="ContactTypeTable"

  def saveEntity(entity:ContactType): Future[ResultSet] ={

    insert
      .value(_.ContactType, entity.ContactType)
      .value(_.name, entity.name)
      .future()

  }

  def getEntity(ContactType: String): Future[Option[ContactType]] = {
    select
      .where(_.ContactType eqs ContactType)
      .one()
  }

  def getEntities: Future[Seq[ContactType]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(ContactType: String): Future[ResultSet] = {
    delete
      .where(_.ContactType eqs ContactType)
      .future()
  }


}
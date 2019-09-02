package repository.address.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.address.ContactType

import scala.concurrent.Future

abstract class ContactTypeTable extends Table[ContactTypeTable, ContactType]{

  object contactTypeId extends StringColumn with PartitionKey

  object name extends StringColumn



}

abstract class ContactTypeTableImpl extends ContactTypeTable with RootConnector {

  override lazy val tableName = "ContactType"

  def saveEntity(entity: ContactType)={
    insert
      .value(_.contactTypeId, entity.contactTypeId)
      .value(_.name, entity.name)
      .future()
  }

  def getEntity(contactTypeId: String): Future[Option[ContactType]] = {
    select
      .where(_.contactTypeId eqs contactTypeId)
      .one()
  }

  def getEntities: Future[Seq[ContactType]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(contactTypeId: String): Future[ResultSet] = {
    delete
      .where(_.contactTypeId eqs contactTypeId)
      .future()
  }
}
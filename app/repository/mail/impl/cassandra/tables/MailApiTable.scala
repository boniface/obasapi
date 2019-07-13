package repository.mail.impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.mail.MailApi

import scala.concurrent.Future

abstract class MailApiTable extends Table[MailApiTable, MailApi] {

  object id extends StringColumn with PartitionKey

  object key extends StringColumn

  object sender extends StringColumn

}

abstract class MailApiTableImpl extends MailApiTable with RootConnector {

  override lazy val tableName = "mailapis"

  def saveEntity(entity: MailApi): Future[ResultSet] = {
    insert
      .value(_.id, entity.id)
      .value(_.key, entity.key)
      .value(_.sender, entity.sender)
      .future()
  }

  def getEntity(id: String): Future[Option[MailApi]] = {
    select
      .where(_.id eqs id)
      .one()
  }

  def getEntities: Future[Seq[MailApi]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(id: String): Future[ResultSet] = {
    delete
      .where(_.id eqs id)
      .future()
  }
}
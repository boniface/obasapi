package repository.mail.Impl.cassandra.tables

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.streams._
import domain.mail.SmtpConfig

import scala.concurrent.Future

abstract class SmtpConfigTable extends Table[SmtpConfigTable, SmtpConfig] {

  object id extends StringColumn with PartitionKey

  object port extends IntColumn

  object host extends StringColumn

  object username extends StringColumn

  object password extends StringColumn


}

abstract class SmtpConfigTableImpl extends SmtpConfigTable with RootConnector {

  override lazy val tableName = "smtps"

  def saveEntity(entity: SmtpConfig): Future[ResultSet] = {
    insert
      .value(_.id, entity.id)
      .value(_.host, entity.host)
      .value(_.port, entity.port)
      .value(_.username, entity.username)
      .value(_.password, entity.password)
      .future()
  }

  def getEntity(id: String): Future[Option[SmtpConfig]] = {
    select
      .where(_.id eqs id)
      .one()
  }

  def getEntities: Future[Seq[SmtpConfig]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(id: String): Future[ResultSet] = {
    delete
      .where(_.id eqs id)
      .future()
  }
}
package repository.mail.impl.cassandra.tables

import java.time.LocalDateTime

import com.outworkers.phantom.dsl._
import com.outworkers.phantom.jdk8._
import com.outworkers.phantom.streams._
import domain.mail.MailConfig

import scala.concurrent.Future

abstract class MailConfigTable extends Table[MailConfigTable, MailConfig] {

  object siteId extends StringColumn with PartitionKey

  object id extends StringColumn with PrimaryKey

  object key extends StringColumn

  object value extends StringColumn

  object host extends StringColumn

  object port extends StringColumn

  object state extends StringColumn

  object date extends  Col[LocalDateTime]

}

abstract class MailConfigTableImpl extends MailConfigTable with RootConnector {

  override lazy val tableName = "mailconfig"

  def saveEntity(entity: MailConfig): Future[ResultSet] = {
    insert
      .value(_.siteId, entity.siteId)
      .value(_.id, entity.id)
      .value(_.key, entity.key)
      .value(_.value, entity.value)
      .value(_.host, entity.host)
      .value(_.port, entity.port)
      .value(_.state, entity.state)
      .value(_.date, entity.date)
      .future()
  }

  def getSiteMailConfigurations(siteId:String): Future[Seq[MailConfig]] = {
    select
      .where(_.siteId eqs siteId)
      .fetchEnumerator() run Iteratee.collect()
  }

  def getEntities: Future[Seq[MailConfig]] = {
    select
      .fetchEnumerator() run Iteratee.collect()
  }

  def deleteEntity(siteId:String, id: String): Future[ResultSet] = {
    delete
      .where(_.siteId eqs siteId)
      .and(_.id eqs id)
      .future()
  }
}

abstract class MailConfigSingleTable extends Table[MailConfigSingleTable, MailConfig] {

  object siteId extends StringColumn with PrimaryKey

  object id extends StringColumn with PartitionKey

  object key extends StringColumn

  object value extends StringColumn

  object host extends StringColumn

  object port extends StringColumn

  object state extends StringColumn

  object date extends  Col[LocalDateTime]

}

abstract class MailConfigSingleTableImpl extends MailConfigSingleTable with RootConnector {

  override lazy val tableName = "mailconfigsingle"

  def saveEntity(entity: MailConfig): Future[ResultSet] = {
    insert
      .value(_.siteId, entity.siteId)
      .value(_.id, entity.id)
      .value(_.key, entity.key)
      .value(_.value, entity.value)
      .value(_.host, entity.host)
      .value(_.port, entity.port)
      .value(_.state, entity.state)
      .value(_.date, entity.date)
      .future()
  }

  def getEntity(id:String): Future[Option[MailConfig]] = {
    select
      .where(_.id eqs id)
      .one()
  }

  def deleteEntity(id: String): Future[ResultSet] = {
    delete
      .where(_.id eqs id)
      .future()
  }
}




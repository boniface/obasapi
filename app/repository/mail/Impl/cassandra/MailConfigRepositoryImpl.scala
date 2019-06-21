package repository.mail.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.mail.MailConfig
import repository.mail.Impl.cassandra.tables.{MailConfigSingleTableImpl, MailConfigTableImpl}
import repository.mail.MailConfigRepository
import util.connections.DataConnection

import scala.concurrent.Future


class MailConfigRepositoryImpl extends MailConfigRepository {

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    MailConfigDatabase.mailConfigTable.create.ifNotExists().future().map(result => result.head.isExhausted())
    MailConfigDatabase.mailConfigSingleTable.create.ifNotExists().future().map(result => result.head.isExhausted())
  }

  override def saveEntity(entity: MailConfig): Future[Boolean] = {
    MailConfigDatabase.mailConfigTable.saveEntity(entity).map(result => result.isExhausted())
    MailConfigDatabase.mailConfigSingleTable.saveEntity(entity).map(result => result.isExhausted())
  }

  override def getEntities: Future[Seq[MailConfig]] = {
    MailConfigDatabase.mailConfigTable.getEntities
  }

  override def getEntity(id: String): Future[Option[MailConfig]] = {
    MailConfigDatabase.mailConfigSingleTable.getEntity(id)
  }

  override def deleteEntity(entity: MailConfig): Future[Boolean] = {
    MailConfigDatabase.mailConfigTable.deleteEntity(entity.siteId,entity.id).map(result => result.isExhausted())
    MailConfigDatabase.mailConfigSingleTable.deleteEntity(entity.id).map(result => result.isExhausted())
  }

  override def getSiteMailConfigurations(siteId: String): Future[Seq[MailConfig]] = {
    MailConfigDatabase.mailConfigTable.getSiteMailConfigurations(siteId)
  }
}


class MailConfigDatabase(override val connector: KeySpaceDef) extends Database[MailConfigDatabase](connector) {
  object mailConfigTable extends MailConfigTableImpl with connector.Connector
  object mailConfigSingleTable extends MailConfigSingleTableImpl with connector.Connector
}

object MailConfigDatabase extends MailConfigDatabase(DataConnection.connector)

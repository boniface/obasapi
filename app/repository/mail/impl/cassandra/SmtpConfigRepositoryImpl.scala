package repository.mail.impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.mail.SmtpConfig
import repository.mail.impl.cassandra.tables.SmtpConfigTableImpl
import repository.mail.SmtpConfigRepository
import util.connections.DataConnection

import scala.concurrent.Future

class SmtpConfigRepositoryImpl extends SmtpConfigRepository {
  override def saveEntity(entity: SmtpConfig): Future[Boolean] = {
    SmtpConfigDatabase.smtpConfigTable.saveEntity(entity).map(result => result.isExhausted())
  }

  override def getEntities: Future[Seq[SmtpConfig]] = {
    SmtpConfigDatabase.smtpConfigTable.getEntities
  }

  override def getEntity(id: String): Future[Option[SmtpConfig]] = {
    SmtpConfigDatabase.smtpConfigTable.getEntity(id)
  }

  override def deleteEntity(entity: SmtpConfig): Future[Boolean] = {
    SmtpConfigDatabase.smtpConfigTable.deleteEntity(entity.id).map(result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace

    implicit def session: Session = DataConnection.connector.session

    SmtpConfigDatabase.smtpConfigTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}

class SmtpConfigDatabase(override val connector: KeySpaceDef) extends Database[SmtpConfigDatabase](connector) {

  object smtpConfigTable extends SmtpConfigTableImpl with connector.Connector

}

object SmtpConfigDatabase extends SmtpConfigDatabase(DataConnection.connector)

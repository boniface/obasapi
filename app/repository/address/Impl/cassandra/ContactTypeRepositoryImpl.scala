package repository.address.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.address.ContactType
import repository.address.Impl.cassandra.tables.ContactTypeTableImpl
import repository.address.ContactTypeRepository
//import util.connections.DataConnection
import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future


class ContactTypeRepositoryImpl extends ContactTypeRepository {
  override def saveEntity(entity: ContactType): Future[Boolean] = {
    ContactTypeDatabase.mailApiTable.saveEntity(entity) map (result => result.isExhausted())

  }

  override def getEntities: Future[Seq[ContactType]] = {
    ContactTypeDatabase.mailApiTable.getEntities
  }

  override def getEntity(contactTypeId: String): Future[Option[ContactType]] = {
    ContactTypeDatabase.mailApiTable.getEntity(contactTypeId)
  }

  override def deleteEntity(entity: ContactType): Future[Boolean] = {
    ContactTypeDatabase.mailApiTable.deleteEntity(entity.contactTypeId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    ContactTypeDatabase.mailApiTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}

class ContactTypeDatabase(override val connector: KeySpaceDef) extends Database[ContactTypeDatabase](connector) {
  object mailApiTable extends ContactTypeTableImpl with connector.Connector

}

object ContactTypeDatabase extends ContactTypeDatabase(DataConnection.connector)

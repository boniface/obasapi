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
    ContactTypeDatabase.ContactTypeTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[ContactType]] = {
    ContactTypeDatabase.ContactTypeTable.getEntities
  }

  override def getEntity(ContactType: String): Future[Option[ContactType]] = {
    ContactTypeDatabase.ContactTypeTable.getEntity(ContactType)
  }

  override def deleteEntity(entity: ContactType): Future[Boolean] = {
    ContactTypeDatabase.ContactTypeTable.deleteEntity(entity.ContactType) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] =
  {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    ContactTypeDatabase.ContactTypeTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}

class ContactTypeDatabase(override val connector: KeySpaceDef) extends Database[ContactTypeDatabase](connector) {
  object ContactTypeTable extends ContactTypeTableImpl with connector.Connector

}

object ContactTypeDatabase extends ContactTypeDatabase(DataConnection.connector)

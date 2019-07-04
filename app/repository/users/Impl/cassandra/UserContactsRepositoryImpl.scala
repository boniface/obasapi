package repository.users.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.users.UserContacts
import repository.users.Impl.cassandra.tables.UserContactsTableImpl
import repository.users.UserContactsRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class UserContactsRepositoryImpl extends UserContactsRepository{
  override def saveEntity(entity: UserContacts): Future[Boolean] = {
    UserContactsDatabase.userContactsTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[UserContacts]] = {
    UserContactsDatabase.userContactsTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserContacts]] = {
    UserContactsDatabase.userContactsTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserContacts): Future[Boolean] = {
    UserContactsDatabase.userContactsTable.deleteEntity(entity.userContactId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UserContactsDatabase.userContactsTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}
class UserContactsDatabase(override val connector: KeySpaceDef) extends Database[UserContactsDatabase](connector) {
  object userContactsTable extends UserContactsTableImpl with connector.Connector

}

object UserContactsDatabase extends UserContactsDatabase(DataConnection.connector)

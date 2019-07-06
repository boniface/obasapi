package repository.users.impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.users.UserDocuments
import repository.users.impl.cassandra.tables.UserDocumentsTableImpl
import repository.users.UserDocumentsRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class UserDocumentsRepositoryImpl extends UserDocumentsRepository{
  override def saveEntity(entity: UserDocuments): Future[Boolean] = {
    UserDocumentsDatabase.userDocumentsTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[UserDocuments]] = {
    UserDocumentsDatabase.userDocumentsTable.getEntities
  }

  override def getEntity(userDocumentsId: String): Future[Option[UserDocuments]] = {
    UserDocumentsDatabase.userDocumentsTable.getEntity(userDocumentsId)
  }

  override def deleteEntity(entity: UserDocuments): Future[Boolean] = {
    UserDocumentsDatabase.userDocumentsTable.deleteEntity(entity.userDocumentsId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UserDocumentsDatabase.userDocumentsTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}
class UserDocumentsDatabase(override val connector: KeySpaceDef) extends Database[UserDocumentsDatabase](connector) {
  object userDocumentsTable extends UserDocumentsTableImpl with connector.Connector

}

object UserDocumentsDatabase extends UserDocumentsDatabase(DataConnection.connector)
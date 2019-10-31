package repository.users.impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.users.UserDocument
import repository.users.impl.cassandra.tables.UserDocumentsTableImpl
import repository.users.UserDocumentRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class UserDocumentRepositoryImpl extends UserDocumentRepository{
  override def saveEntity(entity: UserDocument) = ???

  override def getEntities: Future[Seq[UserDocument]] = {
    UserDocumentsDatabase.userDocumentsTable.getEntities
  }

  override def getEntity(userDocumentsId: String): Future[Option[UserDocument]] = {
    UserDocumentsDatabase.userDocumentsTable.getEntity(userDocumentsId)
  }

  override def deleteEntity(entity: UserDocument): Future[Boolean] = {
    UserDocumentsDatabase.userDocumentsTable.deleteEntity(entity.userId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UserDocumentsDatabase.userDocumentsTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }

  override def getEntity(userId: String, documentId: String): Future[Option[UserDocument]] = ???

  override def getUserDocuments(userId: String): Future[Seq[UserDocument]] = ???
}
class UserDocumentsDatabase(override val connector: KeySpaceDef) extends Database[UserDocumentsDatabase](connector) {
  object userDocumentsTable extends UserDocumentsTableImpl with connector.Connector

}

object UserDocumentsDatabase extends UserDocumentsDatabase(DataConnection.connector)
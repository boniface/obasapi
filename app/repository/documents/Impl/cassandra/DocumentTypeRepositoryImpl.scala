package repository.documents.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.documents.DocumentType
import repository.documents.DocumentTypeRepository
import repository.documents.Impl.cassandra.tables.DocumentTypeTableImpl
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class DocumentTypeRepositoryImpl extends DocumentTypeRepository{
  override def saveEntity(entity: DocumentType): Future[Boolean] = {
    DocumentTypeDatabase.DocumentTypeTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[DocumentType]] = {
    DocumentTypeDatabase.DocumentTypeTable.getEntities
  }

  override def getEntity(documentTypeId: String): Future[Option[DocumentType]] = {
    DocumentTypeDatabase.DocumentTypeTable.getEntity(documentTypeId)
  }

  override def deleteEntity(entity: DocumentType): Future[Boolean] = {
    DocumentTypeDatabase.DocumentTypeTable.deleteEntity(entity.documentTypeId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    DocumentTypeDatabase.DocumentTypeTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}


class  DocumentTypeDatabase(override val connector: KeySpaceDef)extends Database[DocumentTypeDatabase](connector){
  object DocumentTypeTable extends DocumentTypeTableImpl with connector.Connector
}

object DocumentTypeDatabase extends DocumentTypeDatabase(DataConnection.connector)
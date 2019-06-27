package repository.documents.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.documents.Document
import repository.documents.DocumentRepository
import repository.documents.Impl.cassandra.tables.DocumentTableImpl
//import util.connections.DataConnection
import util.connections.{DataConnection, PgDBConnection}


import scala.concurrent.Future

class DocumentRepositoryImpl extends DocumentRepository{
  override def saveEntity(entity: Document): Future[Boolean] = {
    DocumentDatabase.DocumentTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[Document]] = {
    DocumentDatabase.DocumentTable.getEntities
  }

  override def getEntity(email: String): Future[Option[Document]] = {
    DocumentDatabase.DocumentTable.getEntity(email)
  }

  override def deleteEntity(entity: Document): Future[Boolean] ={
    DocumentDatabase.DocumentTable.deleteEntity(entity.email) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    DocumentDatabase.DocumentTable.create.ifNotExists().future().map(result => result.head.isExhausted())
  }
}
class DocumentDatabase(override val connector: KeySpaceDef) extends Database[DocumentDatabase](connector){
  object DocumentTable extends DocumentTableImpl with connector.Connector
  
}

object DocumentDatabase extends DocumentDatabase(DataConnection.connector)
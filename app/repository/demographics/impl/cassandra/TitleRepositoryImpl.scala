package repository.demographics.impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.demographics.Title
import repository.demographics.impl.cassandra.tables.TitleTableImpl
import repository.demographics.TitleRepository
//import util.connections.DataConnection
import util.connections.{DataConnection, PgDBConnection}


import scala.concurrent.Future

class TitleRepositoryImpl extends TitleRepository{
  override def saveEntity(entity: Title) = ???
  override def getEntities: Future[Seq[Title]] = {
    TitleDatabase.TitleTable.getEntities
  }

  override def getEntity(titleId: String): Future[Option[Title]] = {
    TitleDatabase.TitleTable.getEntity(titleId)
  }

  override def deleteEntity(entity: Title): Future[Boolean] = {
    TitleDatabase.TitleTable.deleteEntity(entity.titleId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    TitleDatabase.TitleTable.create.ifNotExists().future().map(result => result.head.isExhausted())
  }
}


 class TitleDatabase(override val connector: KeySpaceDef) extends Database[TitleDatabase](connector){
   object TitleTable extends TitleTableImpl with connector.Connector
 }

object TitleDatabase extends TitleDatabase(DataConnection.connector)

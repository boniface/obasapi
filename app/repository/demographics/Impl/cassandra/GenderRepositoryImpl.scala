package repository.demographics.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.demographics.Gender
import repository.demographics.GenderRepository
import repository.demographics.Impl.cassandra.tables.GenderTableImpl
//import util.connections.DataConnection
import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class GenderRepositoryImpl extends GenderRepository{
  override def saveEntity(entity: Gender): Future[Boolean] = {
    GenderDatabase.GenderTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[Gender]] = {
    GenderDatabase.GenderTable.getEntities
  }

  override def getEntity(genderId: String): Future[Option[Gender]] = {
    GenderDatabase.GenderTable.getEntity(genderId)
  }

  override def deleteEntity(entity: Gender): Future[Boolean] = {
    GenderDatabase.GenderTable.deleteEntity(entity.genderId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    GenderDatabase.GenderTable.create.ifNotExists().future().map(result => result.head.isExhausted())
  }
}

class GenderDatabase(override val connector: KeySpaceDef) extends Database[GenderDatabase](connector){
  object GenderTable extends GenderTableImpl with connector.Connector
}


object GenderDatabase extends GenderDatabase(DataConnection.connector)

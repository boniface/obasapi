package repository.demographics.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.demographics.Roles
import repository.demographics.Impl.cassandra.tables.RolesTableImpl
import repository.demographics.RolesRepository
import util.connections.DataConnection

import scala.concurrent.Future


class RolesRepositoryImpl extends RolesRepository{
  override def saveEntity(entity: Roles): Future[Boolean] = {
    RolesDatabase.RolesTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[Roles]] = {
    RolesDatabase.RolesTable.getEntities
  }

  override def getEntity(id: String): Future[Option[Roles]] = {
    RolesDatabase.RolesTable.getEntity(id)
  }

  override def deleteEntity(entity: Roles): Future[Boolean] = {
    RolesDatabase.RolesTable.deleteEntity(entity.id) map (result => result.isExhausted())
  
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    RolesDatabase.RolesTable.create.ifNotExists().future().map(result => result.head.isExhausted())
  }
}

class RolesDatabase(override val connector: KeySpaceDef) extends Database[RolesDatabase](connector){
  object RolesTable extends RolesTableImpl with connector.Connector
}

object RolesDatabase extends RolesDatabase(DataConnection.connector)

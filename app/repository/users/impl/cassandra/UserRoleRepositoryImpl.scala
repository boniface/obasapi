package repository.users.impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.users.UserRole
import repository.users.impl.cassandra.tables.UserRoleTableImpl
import repository.users.UserRoleRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class UserRoleRepositoryImpl extends UserRoleRepository{
  override def saveEntity(entity: UserRole): Future[Boolean] = {
    UserRoleDatabase.userRoleTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[UserRole]] = {
    UserRoleDatabase.userRoleTable.getEntities
  }

  override def getEntity(userRoleId: String): Future[Option[UserRole]] = {
    UserRoleDatabase.userRoleTable.getEntity(userRoleId)
  }

  override def deleteEntity(entity: UserRole): Future[Boolean] = {
    UserRoleDatabase.userRoleTable.deleteEntity(entity.userRoleId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UserRoleDatabase.userRoleTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}
class UserRoleDatabase(override val connector: KeySpaceDef) extends Database[UserRoleDatabase](connector) {
  object userRoleTable extends UserRoleTableImpl with connector.Connector

}

object UserRoleDatabase extends UserRoleDatabase(DataConnection.connector)
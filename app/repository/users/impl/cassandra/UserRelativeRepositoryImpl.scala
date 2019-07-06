package repository.users.impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.users.UserRelative
import repository.users.impl.cassandra.tables.UserRelativeTableImpl
import repository.users.UserRelativeRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class UserRelativeRepositoryImpl extends UserRelativeRepository{
  override def saveEntity(entity: UserRelative): Future[Boolean] = {
    UserRelativeDatabase.userRelativeTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[UserRelative]] = {
    UserRelativeDatabase.userRelativeTable.getEntities
  }

  override def getEntity(userRelativeId: String): Future[Option[UserRelative]] = {
    UserRelativeDatabase.userRelativeTable.getEntity(userRelativeId)
  }

  override def deleteEntity(entity: UserRelative): Future[Boolean] = {
    UserRelativeDatabase.userRelativeTable.deleteEntity(entity.userRelativeId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UserRelativeDatabase.userRelativeTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}
class UserRelativeDatabase(override val connector: KeySpaceDef) extends Database[UserRelativeDatabase](connector) {
  object userRelativeTable extends UserRelativeTableImpl with connector.Connector

}

object UserRelativeDatabase extends UserRelativeDatabase(DataConnection.connector)
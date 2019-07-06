package repository.users.impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.users.UserResults
import repository.users.impl.cassandra.tables.UserResultsTableImpl
import repository.users.UserResultsRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class UserResultsRepositoryImpl extends UserResultsRepository{
  override def saveEntity(entity: UserResults): Future[Boolean] = {
    UserResultsDatabase.userResultsTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[UserResults]] = {
    UserResultsDatabase.userResultsTable.getEntities
  }

  override def getEntity(userResultsId: String): Future[Option[UserResults]] = {
    UserResultsDatabase.userResultsTable.getEntity(userResultsId)
  }

  override def deleteEntity(entity: UserResults): Future[Boolean] = {
    UserResultsDatabase.userResultsTable.deleteEntity(entity.userResultsId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UserResultsDatabase.userResultsTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}
class UserResultsDatabase(override val connector: KeySpaceDef) extends Database[UserResultsDatabase](connector) {
  object userResultsTable extends UserResultsTableImpl with connector.Connector

}

object UserResultsDatabase extends UserResultsDatabase(DataConnection.connector)
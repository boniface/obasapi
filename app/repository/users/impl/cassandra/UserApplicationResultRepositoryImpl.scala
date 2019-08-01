package repository.users.impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.users.UserApplicationResult
import repository.users.impl.cassandra.tables.UserApplicationResultTableImpl
import repository.users.UserApplicationResultRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class UserApplicationResultRepositoryImpl extends UserApplicationResultRepository{
  override def saveEntity(entity: UserApplicationResult) = ???

  override def getEntities: Future[Seq[UserApplicationResult]] = {
    UserApplicationResultDatabase.userApplicationResultTable.getEntities
  }

  override def getEntity(userApplicationResultId: String): Future[Option[UserApplicationResult]] = {
    UserApplicationResultDatabase.userApplicationResultTable.getEntity(userApplicationResultId)
  }

  override def deleteEntity(entity: UserApplicationResult): Future[Boolean] = {
    UserApplicationResultDatabase.userApplicationResultTable.deleteEntity(entity.userApplicationResultId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UserApplicationResultDatabase.userApplicationResultTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}
class UserApplicationResultDatabase(override val connector: KeySpaceDef) extends Database[UserApplicationResultDatabase](connector) {
  object userApplicationResultTable extends UserApplicationResultTableImpl with connector.Connector

}

object UserApplicationResultDatabase extends UserApplicationResultDatabase(DataConnection.connector)
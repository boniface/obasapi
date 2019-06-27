package repository.users.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.users.UserCommunication
import repository.users.Impl.cassandra.tables.UserCommunicationTableImpl
import repository.users.UserCommunicationRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class UserCommunicationRepositoryImpl extends UserCommunicationRepository{
  override def saveEntity(entity: UserCommunication): Future[Boolean] = {
    UserCommunicationDatabase.userCommunicationTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[UserCommunication]] = {
    UserCommunicationDatabase.userCommunicationTable.getEntities
  }

  override def getEntity(communicationId: String): Future[Option[UserCommunication]] = {
    UserCommunicationDatabase.userCommunicationTable.getEntity(communicationId)
  }

  override def deleteEntity(entity: UserCommunication): Future[Boolean] = {
    UserCommunicationDatabase.userCommunicationTable.deleteEntity(entity.communicationId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UserCommunicationDatabase.userCommunicationTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}
class UserCommunicationDatabase(override val connector: KeySpaceDef) extends Database[UserCommunicationDatabase](connector) {
  object userCommunicationTable extends UserCommunicationTableImpl with connector.Connector

}

object UserCommunicationDatabase extends UserCommunicationDatabase(DataConnection.connector)

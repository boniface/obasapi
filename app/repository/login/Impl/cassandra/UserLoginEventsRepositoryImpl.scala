package repository.login.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.login.UserLoginEvents
import repository.login.Impl.cassandra.tables.UserLoginEventsTable
import repository.login.UserLoginEventsRepository
import util.connections.DataConnection

import scala.concurrent.Future

class UserLoginEventsRepositoryImpl extends UserLoginEventsRepository{
  override def saveEntity(entity: UserLoginEvents) = ???
  override def getEntities: Future[Seq[UserLoginEvents]] = {
    UserLoginEventsDatabase.userLoginEventsTable.getEntities
  }

  override def getUserLoginEvent(email: String, id:String): Future[Option[UserLoginEvents]]={
    UserLoginEventsDatabase.userLoginEventsTable.getEntity(email, id)
  }


  override def getUserLoginEvents(email: String): Future[Seq[UserLoginEvents]]={
    UserLoginEventsDatabase.userLoginEventsTable.getUserLoginEvents(email)
  }


  override def getEntity(id: String): Future[Option[UserLoginEvents]] = {
    UserLoginEventsDatabase.userLoginEventsTable
      .getUserLoginEvents(id).map(events => Some(events.head))
  }

  override def deleteEntity(entity: UserLoginEvents): Future[Boolean] = {
    UserLoginEventsDatabase.userLoginEventsTable.deleteEntity(entity).map(result=> result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UserLoginEventsDatabase.userLoginEventsTable.create.ifNotExists().future().map(result=> result.head.isExhausted())
  }
}

class UserLoginEventsDatabase(override val connector: KeySpaceDef) extends Database[UserLoginEventsDatabase](connector) {
  object userLoginEventsTable extends UserLoginEventsTable with connector.Connector
}

object UserLoginEventsDatabase extends UserLoginEventsDatabase(DataConnection.connector)
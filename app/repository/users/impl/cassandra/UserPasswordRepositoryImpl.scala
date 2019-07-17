package repository.users.impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.users.UserPassword
import repository.users.impl.cassandra.tables.UserPasswordTableImpl
import repository.users.UserPasswordRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class UserPasswordRepositoryImpl extends UserPasswordRepository{
  override def saveEntity(entity: UserPassword): Future[Boolean] = {
    UserPasswordDatabase.userPasswordTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[UserPassword]] = {
    UserPasswordDatabase.userPasswordTable.getEntities
  }

  override def getEntity(userPasswordId: String): Future[Option[UserPassword]] = {
    UserPasswordDatabase.userPasswordTable.getEntity(userPasswordId)
  }

  override def deleteEntity(entity: UserPassword): Future[Boolean] = {
    UserPasswordDatabase.userPasswordTable.deleteEntity(entity.userId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UserPasswordDatabase.userPasswordTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}
class UserPasswordDatabase(override val connector: KeySpaceDef) extends Database[UserPasswordDatabase](connector) {
  object userPasswordTable extends UserPasswordTableImpl with connector.Connector

}

object UserPasswordDatabase extends UserPasswordDatabase(DataConnection.connector)

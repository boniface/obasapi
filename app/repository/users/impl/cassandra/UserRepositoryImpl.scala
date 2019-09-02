package repository.users.impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.users.User
import repository.users.impl.cassandra.tables.UserTableImpl
import repository.users.UserRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class UserRepositoryImpl extends UserRepository{
  override def saveEntity(entity: User) = ???

  override def getEntities: Future[Seq[User]] = {
    UserDatabase.userTable.getEntities
  }

  override def getEntity(email: String): Future[Option[User]] = {
    UserDatabase.userTable.getEntity(email)
  }

  override def deleteEntity(entity: User): Future[Boolean] = {
    UserDatabase.userTable.deleteEntity(entity.email) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UserDatabase.userTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }
}
class UserDatabase(override val connector: KeySpaceDef) extends Database[UserDatabase](connector) {
  object userTable extends UserTableImpl with connector.Connector

}

object UserDatabase extends UserDatabase(DataConnection.connector)
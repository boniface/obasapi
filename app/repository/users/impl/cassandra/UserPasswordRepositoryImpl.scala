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

  override def saveEntity(entity: UserPassword): Future[Boolean] =
  {
    UserPasswordDatabse.UserPasswordTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[UserPassword]] = {
    UserPasswordDatabse.UserPasswordTable.getEntities
  }

  override def getEntity(addressTypeID: String): Future[Option[UserPassword]] =
  {
    UserPasswordDatabse.UserPasswordTable.getEntity(addressTypeID)
  }

  override def deleteEntity(entity: UserPassword): Future[Boolean] = {
    UserPasswordDatabse.UserPasswordTable.deleteEntity(entity.email) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UserPasswordDatabse.UserPasswordTable.create.ifNotExists().future().map(result => result.head.isExhausted())
  }
}

class UserPasswordDatabse(override val connector:KeySpaceDef)extends Database[UserPasswordDatabse](connector){
  object UserPasswordTable extends UserPasswordTableImpl with connector.Connector
}

object UserPasswordDatabse extends UserPasswordDatabse(DataConnection.connector)


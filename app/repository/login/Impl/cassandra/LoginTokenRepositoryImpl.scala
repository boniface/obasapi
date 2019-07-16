package repository.login.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.login.LoginToken
import repository.login.Impl.cassandra.tables.LoginTokenTable
import repository.login.LoginTokenRepository
import util.connections.DataConnection

import scala.concurrent.Future


class LoginTokenRepositoryImpl extends LoginTokenRepository {

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    LoginTokenDatabase.loginTokenTable.create.ifNotExists().future().map(result => result.head.isExhausted())
  }


  override def saveEntity(entity: LoginToken): Future[Boolean] = {
    LoginTokenDatabase.loginTokenTable.saveEntity(entity).map(result => result.isExhausted())
  }

  override def getEntities: Future[Seq[LoginToken]] = {
    LoginTokenDatabase.loginTokenTable.getEntities
  }

  override def getEntity(email: String): Future[Option[LoginToken]] = {
    LoginTokenDatabase.loginTokenTable.getEntity(email)
  }

  override def deleteEntity(entity: LoginToken): Future[Boolean] = {
    LoginTokenDatabase.loginTokenTable.deleteEntity(entity.email).map(result => result.isExhausted())
  }
}


class LoginTokenDatabase(override val connector: KeySpaceDef) extends Database[LoginTokenDatabase](connector) {

  object loginTokenTable extends LoginTokenTable with connector.Connector
}

object LoginTokenDatabase extends LoginTokenDatabase(DataConnection.connector)

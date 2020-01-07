package repository.users.impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.users.UserAddress
import repository.users.impl.cassandra.tables.UserAddressTableImpl
import repository.users.UserAddressRepository
//import util.connections.DataConnection

import util.connections.{DataConnection, PgDBConnection}

import scala.concurrent.Future

class UserAddressRepositoryImpl extends UserAddressRepository{
  override def saveEntity(entity: UserAddress) = ???

  override def getEntities: Future[Seq[UserAddress]] = {
    UserAddressDatabase.userAddressTable.getEntities
  }

  override def getEntity(userAddressId: String): Future[Option[UserAddress]] = {
    UserAddressDatabase.userAddressTable.getEntity(userAddressId)
  }

  override def deleteEntity(entity: UserAddress): Future[Boolean] = {
    UserAddressDatabase.userAddressTable.deleteEntity(entity.userId) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    UserAddressDatabase.userAddressTable.create.ifNotExists().future().map(result => result.head.isExhausted())

  }

  override def getEntity(id: String, addressTypeId: String): Future[Option[UserAddress]] = ???

  override def getEntityForUser(id: String): Future[Seq[UserAddress]] = ???
}
class UserAddressDatabase(override val connector: KeySpaceDef) extends Database[UserAddressDatabase](connector) {
  object userAddressTable extends UserAddressTableImpl with connector.Connector

}

object UserAddressDatabase extends UserAddressDatabase(DataConnection.connector)
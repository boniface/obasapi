package repository.address.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import domain.address.AddressType
import repository.address.AddressTypeRepository
import repository.address.Impl.cassandra.tables.AddressTypeTableImpl
//import util.connections.DataConnection
import util.connections.{DataConnection, PgDBConnection}
//import repository.mail.Impl.cassandra.tables.AddressTypeTableImpl
//import repository.mail.MailApiRepository
//import util.connections.DataConnection

import scala.concurrent.Future

class AddressTypeRepositoryImpl extends AddressTypeRepository {
  override def saveEntity(entity: AddressType): Future[Boolean] =
  {
    AddressTypeDatabse.AddressTypeTable.saveEntity(entity) map (result => result.isExhausted())
  }

  override def getEntities: Future[Seq[AddressType]] = {
    AddressTypeDatabse.AddressTypeTable.getEntities
  }

  override def getEntity(addressTypeID: String): Future[Option[AddressType]] =
  {
    AddressTypeDatabse.AddressTypeTable.getEntity(addressTypeID)
  }

  override def deleteEntity(entity: AddressType): Future[Boolean] = {
    AddressTypeDatabse.AddressTypeTable.deleteEntity(entity.addressTypeID) map (result => result.isExhausted())
  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session
    AddressTypeDatabse.AddressTypeTable.create.ifNotExists().future().map(result => result.head.isExhausted())
  }
}

  class AddressTypeDatabse(override val connector:KeySpaceDef)extends Database[AddressTypeDatabse](connector){
    object AddressTypeTable extends AddressTypeTableImpl with connector.Connector
  }
object AddressTypeDatabse extends AddressTypeDatabse(DataConnection.connector)


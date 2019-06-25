package repository.address.Impl.cassandra

import com.outworkers.phantom.connectors.KeySpace
import com.outworkers.phantom.database.Database
import com.outworkers.phantom.dsl._
import com.sun.jdi.connect.Connector
import domain.address.AddressType
import repository.address.Impl.cassandra.tables.AddressTypeTableImpl
import repository.address.Impl.cassandra.AddressTypeTable
import repository.address.AddressTypeRepository
import util.connections.DataConnection

//import repository.mail.Impl.cassandra.tables.AddressTypeTableImpl
//import repository.mail.MailApiRepository
//import util.connections.DataConnection

import scala.concurrent.Future

class AddressTypeRepositoryImpl extends AddressTypeRepository {
  override def saveEntity(entity: AddressType): Future[Boolean] =
  {

  }

  override def getEntities: Future[Seq[AddressType]] = {

  }

  override def getEntity(addressTypeID: String): Future[Option[AddressType]] =
  {

  }

  override def deleteEntity(entity: AddressType): Future[Boolean] = {

  }

  override def createTable: Future[Boolean] = {
    implicit def keyspace: KeySpace = DataConnection.keySpaceQuery.keySpace
    implicit def session: Session = DataConnection.connector.session


  }
}



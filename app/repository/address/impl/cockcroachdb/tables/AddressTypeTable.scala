package repository.address.impl.cockcroachdb.tables

import domain.address.AddressType
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class AddressTypeTable(tag: Tag) extends Table[AddressType] (tag, "ADDRESS_TYPE"){

  def addressTypeID: Rep[String] = column[String]("ADDRESS_TYPE_ID", O.PrimaryKey)

  def addressName: Rep[String] = column[String]("ADDRESS_NAME")
  
  override def * : ProvenShape[AddressType]  = (addressTypeID,addressName) <> ((AddressType.apply _).tupled,AddressType.unapply)
}

object AddressTypeTable extends TableQuery(new AddressTypeTable(_)){
  def db: driver.api.Database = PgDBConnection.db
  
  def getEntity(addressTypeID:String): Future[Option[AddressType]] = {
    db.run(this.filter(_.addressTypeID === addressTypeID).result).map(_.headOption)
  }

  def saveEntity(addressType: AddressType): Future[AddressType] = {
    db.run(this returning this.map(_.addressTypeID) into ((acc, addressTypeID) => acc.copy(addressTypeID = addressTypeID)) += addressType)
  }

  def getEntities: Future[Seq[AddressType]] = {
    db.run(AddressTypeTable.result)
  }

  def deleteEntity(addressTypeID: String): Future[Int] = {
    db.run(this.filter(_.addressTypeID === addressTypeID).delete)
  }

  def createTable = {
    db.run(
      AddressTypeTable.schema.createIfNotExists
    ).isCompleted
  }
  
  
}

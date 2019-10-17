package repository.users.impl.cockroachdb.tables

import domain.users.UserAddress
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserAddressTableCreate(tag: Tag) extends Table[UserAddress](tag, "user_address") {
  def userId: Rep[String] = column[String]("user_id")

  def addressTypeId: Rep[String] = column[String]("address_type_id")

  def address: Rep[String] = column[String]("address")

  def postalCode: Rep[String] = column[String]("postal_code")

  def * : ProvenShape[UserAddress] = (userId, addressTypeId, address, postalCode) <> ((UserAddress.apply _).tupled, UserAddress.unapply)

  def pk = primaryKey("pk_user_address", (userId, addressTypeId))
}

class UserAddressTable(tag: Tag) extends Table[UserAddress](tag, "user_address") {
  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def addressTypeId: Rep[String] = column[String]("address_type_id", O.PrimaryKey)

  def address: Rep[String] = column[String]("address")

  def postalCode: Rep[String] = column[String]("postal_code")

  def * : ProvenShape[UserAddress] = (userId, addressTypeId, address, postalCode) <> ((UserAddress.apply _).tupled, UserAddress.unapply)
}

object UserAddressTable extends TableQuery(new UserAddressTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String, addressTypeId: String): Future[Option[UserAddress]] = {
    db.run(this.filter(_.userId === userId).filter(_.addressTypeId === addressTypeId).result).map(_.headOption)
  }

  def getEntityForUser(userId: String): Future[Seq[UserAddress]] = {
    db.run(this.filter(_.userId === userId).result)
  }

  def saveEntity(userAddress: UserAddress): Future[Option[UserAddress]] = {
    db.run(
      (this returning this).insertOrUpdate(userAddress)
    )
  }

  def getEntities: Future[Seq[UserAddress]] = {
    db.run(UserAddressTable.result)
  }

  def deleteEntity(userId: String, addressTypeId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).filter(_.addressTypeId === addressTypeId).delete)
  }

}

object UserAddressTableCreate extends TableQuery(new UserAddressTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable = {
    db.run(
      UserAddressTableCreate.schema.createIfNotExists
    ).isCompleted
  }
}
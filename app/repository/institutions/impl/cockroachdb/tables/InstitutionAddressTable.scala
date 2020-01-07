package repository.institutions.impl.cockroachdb.tables

import domain.institutions.InstitutionAddress
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Used for DDL (to create table with composite key)
 * @param tag
 */
class InstitutionAddressTableCreate(tag: Tag) extends Table[InstitutionAddress](tag, "institution_address") {
  def institutionId: Rep[String] = column[String]("institution_id")

  def addressTypeId: Rep[String] = column[String]("address_type_id")

  def address: Rep[String] = column[String]("address")

  def postalCode: Rep[String] = column[String]("postal_code")

  def * : ProvenShape[InstitutionAddress] = (institutionId, addressTypeId, address, postalCode) <> ((InstitutionAddress.apply _).tupled, InstitutionAddress.unapply)

  def pk = primaryKey("pk_institution_address", (institutionId, addressTypeId))
}

object InstitutionAddressTableCreate extends TableQuery(new InstitutionAddressTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable = {
    db.run(
      InstitutionAddressTableCreate.schema.createIfNotExists
    ).isCompleted
  }
}

/**
 * Used for DML
 * @param tag
 */
class InstitutionAddressTable(tag: Tag) extends Table[InstitutionAddress](tag, "institution_address") {

  def institutionId: Rep[String] = column[String]("institution_id", O.PrimaryKey)

  def addressTypeId: Rep[String] = column[String]("address_type_id", O.PrimaryKey)

  def address: Rep[String] = column[String]("address")

  def postalCode: Rep[String] = column[String]("postal_code")

  def * : ProvenShape[InstitutionAddress] = (institutionId, addressTypeId, address, postalCode) <> ((InstitutionAddress.apply _).tupled, InstitutionAddress.unapply)
}

object InstitutionAddressTable extends TableQuery(new InstitutionAddressTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(institutionId: String, addressTypeId: String): Future[Option[InstitutionAddress]] = {
    db.run(this.filter(_.institutionId === institutionId).filter(_.addressTypeId === addressTypeId).result).map(_.headOption)
  }

  def getEntitiesForInstitution(institutionId: String): Future[Seq[InstitutionAddress]] = {
    db.run(this.filter(_.institutionId === institutionId).result)
  }

  def saveEntity(institutionAddress: InstitutionAddress): Future[Option[InstitutionAddress]] = {
    db.run(
      (this returning this).insertOrUpdate(institutionAddress)
    )
  }

  def getEntities: Future[Seq[InstitutionAddress]] = {
    db.run(InstitutionAddressTable.result)
  }

  def deleteEntity(institutionId: String, addressTypeId: String): Future[Int] = {
    db.run(this.filter(_.institutionId === institutionId).filter(_.addressTypeId === addressTypeId).delete)
  }

}

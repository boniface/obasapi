package repository.institutions.impl.cockroachdb.tables

import domain.institutions.InstitutionContact
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
class InstitutionContactTableCreate(tag: Tag) extends Table[InstitutionContact](tag, "institution_contact") {

  def institutionId: Rep[String] = column[String]("institution_id")

  def contactTypeId: Rep[String] = column[String]("contact_type_id")

  def contact: Rep[String] = column[String]("contact")

  override def * : ProvenShape[InstitutionContact] = (institutionId, contactTypeId, contact) <> ((InstitutionContact.apply _).tupled, InstitutionContact.unapply)

  def pk = primaryKey("pk_institution_contact_key", (institutionId, contactTypeId))
}

object InstitutionContactTableCreate extends TableQuery(new InstitutionContactTableCreate(_)) {

  def db: driver.api.Database = PgDBConnection.db

  def createTable = {
    db.run(
      InstitutionContactTableCreate.schema.createIfNotExists
    ).isCompleted
  }

}

/**
 * Used for DML
 * @param tag
 */
class InstitutionContactTable(tag: Tag) extends Table[InstitutionContact](tag, "institution_contact") {

  def institutionId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def contactTypeId: Rep[String] = column[String]("contact_type_id", O.PrimaryKey)

  def contact: Rep[String] = column[String]("contact")

  def * : ProvenShape[InstitutionContact] = (institutionId, contactTypeId, contact) <> ((InstitutionContact.apply _).tupled, InstitutionContact.unapply)
}

object InstitutionContactTable extends TableQuery(new InstitutionContactTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(institutionId: String, contactTypeId: String): Future[Option[InstitutionContact]] = {
    db.run(this.filter(_.institutionId === institutionId).filter(_.contactTypeId === contactTypeId).result).map(_.headOption)
  }

  def getEntitiesForInstitution(institutionId: String): Future[Seq[InstitutionContact]] = {
    db.run(this.filter(_.institutionId === institutionId).result)
  }

  def saveEntity(institutionContact: InstitutionContact): Future[Option[InstitutionContact]] = {
    db.run(
      (this returning this).insertOrUpdate(institutionContact)
    )
  }

  def getEntities: Future[Seq[InstitutionContact]] = {
    db.run(InstitutionContactTable.result)
  }

  def deleteEntity(institutionId: String, contactTypeId: String): Future[Int] = {
    db.run(this.filter(_.institutionId === institutionId).filter(_.contactTypeId === contactTypeId).delete)
  }

}

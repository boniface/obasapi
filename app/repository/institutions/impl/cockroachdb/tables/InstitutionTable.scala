package repository.institutions.impl.cockroachdb.tables

import domain.institutions.Institution
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class InstitutionTable(tag: Tag) extends Table[Institution](tag, "institution"){

  def id: Rep[String] = column[String]("id")

  def institutionTypeId: Rep[String] = column[String]("institution_type_id")

  def name: Rep[String] = column[String]("name")

  override def * : ProvenShape[Institution] = (id, institutionTypeId, name) <> ((Institution.apply _).tupled, Institution.unapply)
}

object InstitutionTable extends TableQuery(new InstitutionTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(id:String): Future[Option[Institution]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(institution: Institution): Future[Option[Institution]] = {
    db.run(
      (this returning this).insertOrUpdate(institution)
    )
  }

  def getEntities: Future[Seq[Institution]] = {
    db.run(InstitutionTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def createTable: Boolean = {
    db.run(
      InstitutionTypeTable.schema.createIfNotExists
    ).isCompleted
  }
}

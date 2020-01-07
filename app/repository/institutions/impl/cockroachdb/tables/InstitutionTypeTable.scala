package repository.institutions.impl.cockroachdb.tables

import domain.institutions.InstitutionType
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class InstitutionTypeTable(tag: Tag) extends Table[InstitutionType] (tag, "institution_type") {

  def id: Rep[String] = column[String]("id", O.PrimaryKey)

  def name: Rep[String] = column[String]("name")

  def description: Rep[Option[String]] = column[Option[String]]("description")

  override def * : ProvenShape[InstitutionType] = (id, name, description) <> ((InstitutionType.apply _).tupled, InstitutionType.unapply)
}

object InstitutionTypeTable extends TableQuery(new InstitutionTypeTable(_)){
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(id:String): Future[Option[InstitutionType]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(institutionType: InstitutionType): Future[Option[InstitutionType]] = {
    db.run(
      (this returning this).insertOrUpdate(institutionType)
    )
  }

  def getEntities: Future[Seq[InstitutionType]] = {
    db.run(InstitutionTypeTable.result)
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
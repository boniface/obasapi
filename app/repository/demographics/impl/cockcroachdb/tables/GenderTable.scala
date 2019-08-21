package repository.demographics.impl.cockcroachdb.tables

import domain.demographics.Gender
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class GenderTable(tag: Tag) extends Table[Gender](tag, _tableName = "gender"){

  def genderId: Rep[String] = column[String]("gender_id", O.PrimaryKey)

  def genderName: Rep[String] = column[String]("gender_name")



  def * : ProvenShape[Gender] = (genderId, genderName) <> ((Gender.apply _).tupled, Gender.unapply)
}

object GenderTable extends TableQuery(new GenderTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(genderId: String): Future[Option[Gender]] = {
    db.run(this.filter(_.genderId === genderId).result).map(_.headOption)
  }

  def saveEntity(gender: Gender): Future[Option[Gender]] = {
    db.run(
      (this returning this).insertOrUpdate(gender)
    )
  }

  def getEntities: Future[Seq[Gender]] = {
    db.run(GenderTable.result)
  }

  def deleteEntity(genderId: String): Future[Int] = {
    db.run(this.filter(_.genderId === genderId).delete)
  }

  def createTable = {
    db.run(
      GenderTable.schema.createIfNotExists
    ).isCompleted
  }
  
}

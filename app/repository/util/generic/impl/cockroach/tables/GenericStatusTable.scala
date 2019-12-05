package repository.util.generic.impl.cockroach.tables

import domain.util.generic.GenericStatus
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class GenericStatusTable(tag: Tag) extends Table[GenericStatus] (tag, _tableName = "generic_status") {

  def id: Rep[String] = column[String]("id", O.PrimaryKey)

  def name: Rep[String] = column[String]("name")

  def description: Rep[Option[String]] = column[Option[String]]("description")

  override def * : ProvenShape[GenericStatus] = (id, name, description) <> ((GenericStatus.apply _).tupled, GenericStatus.unapply)
}

object GenericStatusTable extends TableQuery(new GenericStatusTable(_)){
  def db: driver.api.Database =PgDBConnection.db

  def getEntity(id:String):Future[Option[GenericStatus]] ={
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(applicationStatus: GenericStatus): Future[Option[GenericStatus]] = {
    db.run(
      (this returning this).insertOrUpdate(applicationStatus)
    )
  }

  def getEntities: Future[Seq[GenericStatus]] = {
    db.run(GenericStatusTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def createTable = {
    db.run(
      GenericStatusTable.schema.createIfNotExists
    ).isCompleted
  }
  
}
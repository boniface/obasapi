package repository.application.impl.cockcroachdb.tables

import domain.application.ApplicationType
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApplicationTypeTable(tag: Tag) extends Table[ApplicationType](tag, _tableName = "application_type"){

  def id: Rep[String] = column[String]("id", O.PrimaryKey)

  def name: Rep[String] = column[String]("name")

  def description: Rep[Option[String]] = column[Option[String]]("description")

  override def * : ProvenShape[ApplicationType] = (id, name, description) <> ((ApplicationType.apply _).tupled, ApplicationType.unapply)
}

object ApplicationTypeTable extends TableQuery(new ApplicationTypeTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(id: String): Future[Option[ApplicationType]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(applicationType: ApplicationType): Future[Option[ApplicationType]] = {
    db.run(
      (this returning this).insertOrUpdate(applicationType)
    )
  }

  def getEntities: Future[Seq[ApplicationType]] = {
    db.run(ApplicationTypeTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def createTable = {
    db.run(
      ApplicationTypeTable.schema.createIfNotExists
    ).isCompleted
  }

}

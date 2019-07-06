package repository.application.impl.cockcroachdb.tables

//import akka.http.scaladsl.model.DateTime
import java.time.LocalDateTime

import domain.application.ApplicationResult
import org.joda.time.DateTime
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApplicationResultTable(tag: Tag) extends Table[ApplicationResult] (tag, _tableName = "APPLICATION_RESULT"){

  def applicationResultId: Rep[String] = column[String]("APPLICATION_RESULT_ID", O.PrimaryKey)

  def description: Rep[String] = column[String]("DESCRIPTION")

  def date: Rep[LocalDateTime] = column[LocalDateTime]("DATE")

  def * : ProvenShape[ApplicationResult] = (applicationResultId, description, date) <> ((ApplicationResult.apply _).tupled, ApplicationResult.unapply)
}

object ApplicationResultTable extends TableQuery(new ApplicationResultTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(applicationResultId: String): Future[Option[ApplicationResult]] = {
    db.run(this.filter(_.applicationResultId === applicationResultId).result).map(_.headOption)
  }

  def saveEntity(applicationResult: ApplicationResult): Future[ApplicationResult] = {
    db.run(this returning this.map(_.applicationResultId) into ((acc, applicationResultId) => acc.copy(applicationResultId = applicationResultId)) += applicationResult)
  }

  def getEntities: Future[Seq[ApplicationResult]] = {
    db.run(ApplicationResultTable.result)
  }

  def deleteEntity(applicationResultId: String): Future[Int] = {
    db.run(this.filter(_.applicationResultId === applicationResultId).delete)
  }

  def createTable = {
    db.run(
      ApplicationResultTable.schema.createIfNotExists
    ).isCompleted
  }

}
package repository.demographics.impl.cockcroachdb.tables

import domain.demographics.Roles
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class RolesTable(tag: Tag) extends Table[Roles] (tag, _tableName = "ROLES"){

  def id: Rep[String] = column[String]("ID", O.PrimaryKey)

  def roleName: Rep[String] = column[String]("ROLE_NAME")



  def * : ProvenShape[ Roles] = (id, roleName) <> (( Roles.apply _).tupled,  Roles.unapply)
}

object RolesTable extends TableQuery(new RolesTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(id: String): Future[Option[ Roles]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(roles: Roles): Future[Option[Roles]] = {
    db.run(
      (this returning this).insertOrUpdate(roles)
    )
  }

  def getEntities: Future[Seq[ Roles]] = {
    db.run(RolesTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def createTable = {
    db.run(
      RolesTable.schema.createIfNotExists
    ).isCompleted
  }
  
}

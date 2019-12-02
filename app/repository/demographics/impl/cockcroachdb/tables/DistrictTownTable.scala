package repository.demographics.impl.cockcroachdb.tables

import domain.demographics.DistrictTown
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Used for DDL (create table in DB) with composite key
 * @param tag
 */
class DistrictTownTableCreate(tag: Tag) extends Table[DistrictTown](tag, "district_town") {

  def districtCode: Rep[String] = column[String]("district_code")

  def townCode: Rep[String] = column[String]("town_code")

  override def * : ProvenShape[DistrictTown] = (districtCode, townCode) <> ((DistrictTown.apply _).tupled, DistrictTown.unapply)

  def pk = primaryKey("pk_district_town", (districtCode, townCode))
}

object DistrictTownTableCreate extends TableQuery(new DistrictTownTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable: Boolean = {
    db.run(DistrictTownTableCreate.schema.createIfNotExists).isCompleted
  }
}

/**
 * Used for DML
 * @param tag
 */
class DistrictTownTable(tag: Tag) extends Table[DistrictTown](tag, "district_town") {

  def districtCode: Rep[String] = column[String]("district_code", O.PrimaryKey)

  def townCode: Rep[String] = column[String]("town_code", O.PrimaryKey)

  override def * : ProvenShape[DistrictTown] = (districtCode, townCode) <> ((DistrictTown.apply _).tupled, DistrictTown.unapply)
}

object DistrictTownTable extends TableQuery(new DistrictTownTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(districtCode: String, townCode: String): Future[Option[DistrictTown]] = {
    db.run(this.filter(_.districtCode === districtCode).filter(_.townCode === townCode).result).map(_.headOption)
  }

  def saveEntity(districtTown: DistrictTown): Future[Option[DistrictTown]] = {
    db.run((this returning this).insertOrUpdate(districtTown))
  }

  def getEntitiesForDistrict(districtCode: String): Future[Seq[DistrictTown]] = {
    db.run(this.filter(_.districtCode === districtCode).result)
  }

  def getEntities : Future[Seq[DistrictTown]] = {
    db.run(DistrictTownTable.result)
  }

  def deleteEntity(districtCode: String, townCode: String): Future[Int] = {
    db.run(this.filter(_.districtCode === districtCode).filter(_.townCode === townCode).delete)
  }

}

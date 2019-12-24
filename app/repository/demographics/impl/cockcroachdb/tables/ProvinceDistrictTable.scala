package repository.demographics.impl.cockcroachdb.tables

import domain.demographics.ProvinceDistrict
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Used for DDL (to create table in DB) with composite key
 * @param tag
 */
@Deprecated
class ProvinceDistrictTableCreate(tag: Tag) extends Table[ProvinceDistrict](tag, "province_district") {
  def provinceCode: Rep[String] = column[String]("province_code")

  def districtCode: Rep[String] = column[String]("district_code")

  override def * : ProvenShape[ProvinceDistrict] = (provinceCode, districtCode) <> ((ProvinceDistrict.apply _).tupled, ProvinceDistrict.unapply)

  def pk = primaryKey("pk_province_district", (provinceCode, districtCode))
}

@Deprecated
object ProvinceDistrictTableCreate extends TableQuery(new ProvinceDistrictTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable: Boolean = {
    db.run(ProvinceDistrictTableCreate.schema.createIfNotExists).isCompleted
  }
}

/**
 * Used for DML
 * @param tag
 */
@Deprecated
class ProvinceDistrictTable(tag: Tag) extends Table[ProvinceDistrict](tag, "province_district") {
  def provinceCode: Rep[String] = column[String]("province_code", O.PrimaryKey)

  def districtCode: Rep[String] = column[String]("district_code", O.PrimaryKey)

  override def * : ProvenShape[ProvinceDistrict] = (provinceCode, districtCode) <> ((ProvinceDistrict.apply _).tupled, ProvinceDistrict.unapply)
}

@Deprecated
object ProvinceDistrictTable extends TableQuery(new ProvinceDistrictTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(provinceCode: String, districtCode: String): Future[Option[ProvinceDistrict]] = {
    db.run(this.filter(_.provinceCode === provinceCode).filter(_.districtCode === districtCode).result).map(_.headOption)
  }

  def saveEntity(provinceDistrict: ProvinceDistrict): Future[Option[ProvinceDistrict]] = {
    db.run((this returning this).insertOrUpdate(provinceDistrict))
  }

  def getEntitiesForProvince(provinceCode: String): Future[Seq[ProvinceDistrict]] = {
    db.run(this.filter(_.provinceCode === provinceCode).result)
  }

  def getEntities : Future[Seq[ProvinceDistrict]] = {
    db.run(ProvinceDistrictTable.result)
  }

  def deleteEntity(provinceCode: String, districtCode: String): Future[Int] = {
    db.run(this.filter(_.provinceCode === provinceCode).filter(_.districtCode === districtCode).delete)
  }

}

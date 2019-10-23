package repository.district.impl.cockroachdb.tables

import java.time.LocalDateTime

import domain.district.District
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class DistrictTable(tag: Tag) extends Table[District] (tag, _tableName = "application_status") {

  def districtCode: Rep[String] = column[String]("district_code", O.PrimaryKey)

  def districtName: Rep[String] = column[String]("district_name")

  override def * : ProvenShape[District] = (districtCode,districtName) <> ((District.apply _).tupled, District.unapply)
}

object DistrictTable extends TableQuery(new DistrictTable(_)){
  def db: driver.api.Database =PgDBConnection.db

  def getEntity(districtCode:String):Future[Option[District]] ={
    db.run(this.filter(_.districtCode === districtCode).result).map(_.headOption)
  }

  def saveEntity(district: District): Future[Option[District]] = {
    db.run(
      (this returning this).insertOrUpdate(district)
    )
  }

  def getEntities: Future[Seq[District]] = {
    db.run(DistrictTable.result)
  }

  def deleteEntity(districtCode: String): Future[Int] = {
    db.run(this.filter(_.districtCode === districtCode).delete)
  }

  def createTable = {
    db.run(
      DistrictTable.schema.createIfNotExists
    ).isCompleted
  }
}

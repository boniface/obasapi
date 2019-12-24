package repository.demographics.impl.cockcroachdb.tables

import domain.demographics.Province
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Deprecated
class ProvinceTable(tag: Tag) extends Table[Province] (tag, _tableName = "province") {

  def provinceCode: Rep[String] = column[String]("province_code", O.PrimaryKey)

  def provinceName: Rep[String] = column[String]("province_name")

  override def * : ProvenShape[Province] = (provinceCode,provinceName) <> ((Province.apply _).tupled, Province.unapply)
}

@Deprecated
object ProvinceTable extends TableQuery(new ProvinceTable(_)){
  def db: driver.api.Database =PgDBConnection.db

  def getEntity(provinceCode:String):Future[Option[Province]] ={
    db.run(this.filter(_.provinceCode === provinceCode).result).map(_.headOption)
  }

  def saveEntity(province: Province): Future[Option[Province]] = {
    db.run(
      (this returning this).insertOrUpdate(province)
    )
  }

  def getEntities: Future[Seq[Province]] = {
    db.run(ProvinceTable.result)
  }

  def deleteEntity(provinceCode: String): Future[Int] = {
    db.run(this.filter(_.provinceCode === provinceCode).delete)
  }

  def createTable = {
    db.run(
      ProvinceTable.schema.createIfNotExists
    ).isCompleted
  }

}
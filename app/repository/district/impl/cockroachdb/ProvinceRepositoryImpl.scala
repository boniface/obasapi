package repository.district.impl.cockroachdb

import domain.district.Province
import repository.district.ProvinceRepository
import repository.district.impl.cockroachdb.tables.ProvinceTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ProvinceRepositoryImpl extends ProvinceRepository{
  override def saveEntity(entity: Province): Future[Option[Province]] = {
    ProvinceTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Province]] = {
    ProvinceTable.getEntities
  }

  override def getEntity(provinceCode: String): Future[Option[Province]] = {
    ProvinceTable.getEntity(provinceCode)
  }

  override def deleteEntity(entity: Province): Future[Boolean] = {
    ProvinceTable.deleteEntity(entity.provinceCode)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(ProvinceTable.createTable)
  }
}

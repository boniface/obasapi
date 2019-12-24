package repository.demographics.impl.cockcroachdb

import domain.demographics.Province
import repository.demographics.ProvinceRepository
import repository.demographics.impl.cockcroachdb.tables.ProvinceTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Deprecated
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

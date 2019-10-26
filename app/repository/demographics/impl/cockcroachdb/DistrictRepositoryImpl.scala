package repository.demographics.impl.cockcroachdb

import domain.demographics.District
import repository.demographics.DistrictRepository
import repository.demographics.impl.cockcroachdb.tables.DistrictTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class DistrictRepositoryImpl extends DistrictRepository{
  override def saveEntity(entity: District): Future[Option[District]] = {
    DistrictTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[District]] = {
    DistrictTable.getEntities
  }

  override def getEntity(districtCode: String): Future[Option[District]] = {
    DistrictTable.getEntity(districtCode)
  }

  override def deleteEntity(entity: District): Future[Boolean] = {
    DistrictTable.deleteEntity(entity.districtCode)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(DistrictTable.createTable)
  }
}

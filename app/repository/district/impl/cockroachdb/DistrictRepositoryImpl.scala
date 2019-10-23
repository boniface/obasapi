package repository.district.impl.cockroachdb

import domain.district.District
import repository.district.DistrictRepository
import repository.district.impl.cockroachdb.tables.DistrictTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


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

package repository.demographics.impl.cockcroachdb

import domain.demographics.ProvinceDistrict
import repository.demographics.ProvinceDistrictRepository
import repository.demographics.impl.cockcroachdb.tables.{ProvinceDistrictTable, ProvinceDistrictTableCreate}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Deprecated
class ProvinceDistrictRepositoryImpl extends ProvinceDistrictRepository {
  override def saveEntity(entity: ProvinceDistrict): Future[Option[ProvinceDistrict]] = ProvinceDistrictTable.saveEntity(entity)

  override def getEntities: Future[Seq[ProvinceDistrict]] = ProvinceDistrictTable.getEntities

  override def getEntity(provinceCode: String, districtCode: String): Future[Option[ProvinceDistrict]] = ProvinceDistrictTable.getEntity(provinceCode, districtCode)

  override def deleteEntity(entity: ProvinceDistrict): Future[Boolean] = ProvinceDistrictTable.deleteEntity(entity.provinceCode, entity.districtCode).map(value => value.isValidInt)

  override def getEntity(id: String): Future[Option[ProvinceDistrict]] = ???

  override def getEntitiesForProvince(provinceCode: String): Future[Seq[ProvinceDistrict]] = ProvinceDistrictTable.getEntitiesForProvince(provinceCode)

  override def createTable: Future[Boolean] = Future.successful(ProvinceDistrictTableCreate.createTable)
}

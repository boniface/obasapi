package services.demographics.Impl.cockroachdb

import domain.demographics.ProvinceDistrict
import repository.demographics.ProvinceDistrictRepository
import services.demographics.ProvinceDistrictService

import scala.concurrent.Future

class ProvinceDistrictServiceImpl extends ProvinceDistrictService {
  override def getEntity(provinceCode: String, districtCode: String): Future[Option[ProvinceDistrict]] =
    ProvinceDistrictRepository.apply.getEntity(provinceCode, districtCode)

  override def getEntitiesForProvince(provinceCode: String): Future[Seq[ProvinceDistrict]] =
    ProvinceDistrictRepository.apply.getEntitiesForProvince(provinceCode)

  override def saveEntity(entity: ProvinceDistrict): Future[Option[ProvinceDistrict]] =
    ProvinceDistrictRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[ProvinceDistrict]] =
    ProvinceDistrictRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[ProvinceDistrict]] = ???

  override def deleteEntity(entity: ProvinceDistrict): Future[Boolean] = ProvinceDistrictRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = ProvinceDistrictRepository.apply.createTable
}

package repository.demographics.impl.cockcroachdb

import domain.demographics.DistrictTown
import repository.demographics.DistrictTownRepository
import repository.demographics.impl.cockcroachdb.tables.{DistrictTownTable, DistrictTownTableCreate}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class DistrictTownRepositoryImpl extends DistrictTownRepository {
  override def getEntity(districtCode: String, townCode: String): Future[Option[DistrictTown]] = DistrictTownTable.getEntity(districtCode, townCode)

  override def getEntitiesForDistrict(districtCode: String): Future[Seq[DistrictTown]] =
    DistrictTownTable.getEntitiesForDistrict(districtCode)

  override def saveEntity(entity: DistrictTown): Future[Option[DistrictTown]] = DistrictTownTable.saveEntity(entity)

  override def getEntities: Future[Seq[DistrictTown]] = DistrictTownTable.getEntities

  override def getEntity(id: String): Future[Option[DistrictTown]] = ???

  override def deleteEntity(entity: DistrictTown): Future[Boolean] = DistrictTownTable.deleteEntity(entity.districtCode, entity.townCode).map(value => value.isValidInt)

  override def createTable: Future[Boolean] = Future.successful(DistrictTownTableCreate.createTable)
}

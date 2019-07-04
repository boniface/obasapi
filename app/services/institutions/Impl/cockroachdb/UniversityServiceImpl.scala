package services.institutions.Impl.cockroachdb

import domain.institutions.University
import services.institutions.UniversityService

import scala.concurrent.Future

class UniversityServiceImpl extends UniversityService {

  override def saveEntity(entity: University): Future[Boolean] =
    UniversityService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[University]] =
    UniversityService.roach.getEntities

  override def getEntity(userAddressId: String): Future[Option[University]] =
    UniversityService.roach.getEntity(userAddressId)

  override def deleteEntity(entity: University): Future[Boolean] =
    UniversityService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UniversityService.roach.createTable

}

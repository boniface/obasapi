package services.institutions.Impl.cockroachdb

import domain.institutions.University
import repository.institutions.UniversityRepository
import services.institutions.UniversityService

import scala.concurrent.Future

class UniversityServiceImpl extends UniversityService {

  override def saveEntity(entity: University): Future[Boolean] =
    UniversityRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[University]] =
    UniversityRepository.roach.getEntities

  override def getEntity(universityId: String): Future[Option[University]] =
    UniversityRepository.roach.getEntity(universityId)

  override def deleteEntity(entity: University): Future[Boolean] =
    UniversityRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UniversityRepository.roach.createTable

}

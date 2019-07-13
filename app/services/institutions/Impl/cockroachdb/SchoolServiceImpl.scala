package services.institutions.Impl.cockroachdb

import domain.institutions.School
import repository.institutions.SchoolRepository
import services.institutions.SchoolService

import scala.concurrent.Future

class SchoolServiceImpl extends SchoolService {

  override def saveEntity(entity: School): Future[Boolean] =
    SchoolRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[School]] =
    SchoolRepository.roach.getEntities

  override def getEntity(schoolId: String): Future[Option[School]] =
    SchoolRepository.roach.getEntity(schoolId)

  override def deleteEntity(entity: School): Future[Boolean] =
    SchoolRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    SchoolRepository.roach.createTable

}
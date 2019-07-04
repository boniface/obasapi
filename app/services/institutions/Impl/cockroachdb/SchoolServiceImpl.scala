package services.institutions.Impl.cockroachdb

import domain.institutions.School
import services.institutions.SchoolService

import scala.concurrent.Future

class SchoolServiceImpl extends SchoolService {

  override def saveEntity(entity: School): Future[Boolean] =
    SchoolService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[School]] =
    SchoolService.roach.getEntities

  override def getEntity(userAddressId: String): Future[Option[School]] =
    SchoolService.roach.getEntity(userAddressId)

  override def deleteEntity(entity: School): Future[Boolean] =
    SchoolService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    SchoolService.roach.createTable

}
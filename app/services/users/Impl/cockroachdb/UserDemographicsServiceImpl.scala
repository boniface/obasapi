package services.users.Impl.cockroachdb

import domain.users.UserDemographics
import repository.users.UserDemographicsRepository
import services.users.UserDemographicsService

import scala.concurrent.Future

class UserDemographicsServiceImpl extends UserDemographicsService{

  override def saveEntity(entity: UserDemographics): Future[Boolean] =
    UserDemographicsRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserDemographics]] =
    UserDemographicsRepository.roach.getEntities

  override def getEntity(userDemographicsId: String): Future[Option[UserDemographics]] =
    UserDemographicsRepository.roach.getEntity(userDemographicsId)

  override def deleteEntity(entity: UserDemographics): Future[Boolean] =
    UserDemographicsService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserDemographicsRepository.roach.createTable

}

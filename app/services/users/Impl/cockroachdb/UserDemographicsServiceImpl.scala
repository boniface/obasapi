package services.users.Impl.cockroachdb

import domain.users.UserDemographics
import services.users.UserDemographicsService

import scala.concurrent.Future

class UserDemographicsServiceImpl extends UserDemographicsService{

  override def saveEntity(entity: UserDemographics): Future[Boolean] =
    UserDemographicsService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserDemographics]] =
    UserDemographicsService.roach.getEntities

  override def getEntity(userAddressId: String): Future[Option[UserDemographics]] =
    UserDemographicsService.roach.getEntity(userAddressId)

  override def deleteEntity(entity: UserDemographics): Future[Boolean] =
    UserDemographicsService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserDemographicsService.roach.createTable

}

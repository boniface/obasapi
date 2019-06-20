package services.users.Impl

import services.users.UserDemographicsService
import domain.users.UserDemographics

import scala.concurrent.Future

class UserDemographicsServiceImpl extends UserDemographicsService{

  override def saveEntity(entity: UserDemographics): Future[Boolean] = ???

  override def getEntities: Future[Seq[UserDemographics]] = ???

  override def getEntity(id: String): Future[Option[UserDemographics]] = ???

  override def deleteEntity(entity: UserDemographics): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???

}

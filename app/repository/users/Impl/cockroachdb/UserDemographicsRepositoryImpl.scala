package repository.users.Impl.cockroachdb

import domain.users.UserDemographics
import repository.users.Impl.cockroachdb.tables.UserDemographicsTable
import repository.users.UserDemographicsRepository

import scala.concurrent.Future

class UserDemographicsRepositoryImpl  extends UserDemographicsRepository{

  override def saveEntity(entity: UserDemographics): Future[Boolean] = {
    UserDemographicsRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserDemographics]] = {
    UserDemographicsRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserDemographics]] = {
    UserDemographicsRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserDemographics): Future[Boolean] = {
    UserDemographicsRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UserDemographicsRepository.roach.createTable
  }
}

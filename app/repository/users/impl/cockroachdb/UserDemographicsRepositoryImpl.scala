package repository.users.impl.cockroachdb

import domain.users.UserDemographics
import repository.users.impl.cockroachdb.tables.UserDemographicsTable
import repository.users.UserDemographicsRepository

import scala.concurrent.Future

class UserDemographicsRepositoryImpl  extends UserDemographicsRepository{

  override def saveEntity(entity: UserDemographics): Future[Boolean] = {
    Future.successful(UserDemographicsTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserDemographics]] = {
    UserDemographicsTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserDemographics]] = {
    UserDemographicsTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserDemographics): Future[Boolean] = {
    Future.successful(UserDemographicsTable.deleteEntity(entity.userDemographicsId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserDemographicsTable.createTable)
  }
}

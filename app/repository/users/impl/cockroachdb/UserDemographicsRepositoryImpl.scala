package repository.users.impl.cockroachdb

import domain.users.UserDemographics
import repository.users.impl.cockroachdb.tables.UserDemographicsTable
import repository.users.UserDemographicsRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserDemographicsRepositoryImpl  extends UserDemographicsRepository{

  override def saveEntity(entity: UserDemographics): Future[Boolean] = {
    UserDemographicsTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[UserDemographics]] = {
    UserDemographicsTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserDemographics]] = {
    UserDemographicsTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserDemographics): Future[Boolean] = {
    UserDemographicsTable.deleteEntity(entity.userDemographicsId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserDemographicsTable.createTable)
  }
}



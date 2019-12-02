package repository.users.impl.cockroachdb

import domain.users.UserDemographics
import repository.users.impl.cockroachdb.tables.UserDemographicsTable
import repository.users.UserDemographicsRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserDemographicsRepositoryImpl  extends UserDemographicsRepository{

  override def saveEntity(entity: UserDemographics): Future[Option[UserDemographics]] = {
    UserDemographicsTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserDemographics]] = {
    UserDemographicsTable.getEntities
  }

  override def getEntity(userDemographicsId: String): Future[Option[UserDemographics]] = {
    UserDemographicsTable.getEntity(userDemographicsId)
  }

  override def deleteEntity(entity: UserDemographics): Future[Boolean] = {
    UserDemographicsTable.deleteEntity(entity.userId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserDemographicsTable.createTable)
  }
}



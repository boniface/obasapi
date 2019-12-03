package repository.users.impl.cockroachdb

import domain.users.UserApplication
import repository.users.UserApplicationRepository
import repository.users.impl.cockroachdb.tables.{UserApplicationTable, UserApplicationTableCreate}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserApplicationRepositoryImpl  extends UserApplicationRepository{

  override def getEntity(userId: String, applicationId: String): Future[Option[UserApplication]] =
  {
    UserApplicationTable.getEntity(userId,applicationId)
  }
  override def getEntityForUser(userId: String): Future[Seq[UserApplication]] = {
    UserApplicationTable.getEntityForUser(userId)
  }

  override def saveEntity(entity: UserApplication): Future[Option[UserApplication]] ={
    UserApplicationTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserApplication]] ={
    UserApplicationTable.getEntities
  }

  override def deleteEntity(entity: UserApplication): Future[Boolean] = {
    UserApplicationTable.deleteEntity(entity.userId, entity.applicationId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserApplicationTableCreate.createTable)
  }

  override def getEntity(id: String): Future[Option[UserApplication]] = ???



}



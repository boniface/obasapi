package repository.login.impl.cockroachdb

import domain.login.UserLoginEvents
import repository.login.UserLoginEventsRepository
import repository.login.impl.cockroachdb.tables.UserLoginEventsTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserLoginEventsRepositoryImpl extends UserLoginEventsRepository{

  override def saveEntity(entity: UserLoginEvents): Future[Boolean] = {
    UserLoginEventsTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[UserLoginEvents]] = {
    UserLoginEventsTable.getEntities
  }

  override def getEntity(id: String): Future[Option[UserLoginEvents]] = {
    UserLoginEventsTable.getEntity(id)
  }

  override def deleteEntity(entity: UserLoginEvents): Future[Boolean] = {
    UserLoginEventsTable.deleteEntity(entity.id)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserLoginEventsTable.createTable)
  }

  override def getUserLoginEvent(email: String, id: String): Future[Option[UserLoginEvents]] = ???

  override def getUserLoginEvents(email: String): Future[Seq[UserLoginEvents]] = ???
}



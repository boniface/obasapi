package services.login.Impl

import domain.login.UserLoginEvents
import repository.login.UserLoginEventsRepository
import services.login.UserLoginEventsService

import scala.concurrent.Future

class UserLoginEventsServiceImpl extends UserLoginEventsService {
  override def saveEntity(entity: UserLoginEvents): Future[Option[UserLoginEvents]] = {
    UserLoginEventsRepository.apply.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserLoginEvents]] = {
    UserLoginEventsRepository.apply.getEntities
  }

  override def getEntity(id: String): Future[Option[UserLoginEvents]] = {
    UserLoginEventsRepository.apply.getEntity(id)
  }

  override def deleteEntity(entity: UserLoginEvents): Future[Boolean] = {
    UserLoginEventsRepository.apply.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UserLoginEventsRepository.apply.createTable
  }
}

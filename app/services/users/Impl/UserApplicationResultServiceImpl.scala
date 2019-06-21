package services.users.Impl

import domain.users.UserApplicationResult
import services.users.UserApplicationResultService

import scala.concurrent.Future

class UserApplicationResultServiceImpl extends  UserApplicationResultService {

  override def saveEntity(entity: UserApplicationResult): Future[Boolean] = ???

  override def getEntities: Future[Seq[UserApplicationResult]] = ???

  override def getEntity(id: String): Future[Option[UserApplicationResult]] = ???

  override def deleteEntity(entity: UserApplicationResult): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???

}

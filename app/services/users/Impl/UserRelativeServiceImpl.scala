package services.users.Impl

import domain.users.UserRelative
import services.users.UserRelativeService

import scala.concurrent.Future

class UserRelativeServiceImpl extends UserRelativeService {

  override def saveEntity(entity: UserRelative): Future[Boolean] = ???

  override def getEntities: Future[Seq[UserRelative]] = ???

  override def getEntity(userRelativeId: String): Future[Option[UserRelative]] = ???

  override def deleteEntity(entity: UserRelative): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???

}

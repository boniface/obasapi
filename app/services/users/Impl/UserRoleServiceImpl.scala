package services.users.Impl

import domain.users.UserRole
import services.users.UserRoleService

import scala.concurrent.Future

class UserRoleServiceImpl extends UserRoleService {

  override def saveEntity(entity: UserRole): Future[Boolean] = ???

  override def getEntities: Future[Seq[UserRole]] = ???

  override def getEntity(userRoleId: String): Future[Option[UserRole]] = ???

  override def deleteEntity(entity: UserRole): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???

}

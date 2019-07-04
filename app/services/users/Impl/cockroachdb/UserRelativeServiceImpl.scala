package services.users.Impl.cockroachdb

import domain.users.UserRelative
import services.users.UserRelativeService

import scala.concurrent.Future

class UserRelativeServiceImpl extends UserRelativeService {

  override def saveEntity(entity: UserRelative): Future[Boolean] =
    UserRelativeService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserRelative]] =
    UserRelativeService.roach.getEntities

  override def getEntity(userAddressId: String): Future[Option[UserRelative]] =
    UserRelativeService.roach.getEntity(userAddressId)

  override def deleteEntity(entity: UserRelative): Future[Boolean] =
    UserRelativeService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserRelativeService.roach.createTable

}

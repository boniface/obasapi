package services.users.Impl

import domain.users.UserRelative
import repository.users.UserRelativeRepository
import services.users.UserRelativeService

import scala.concurrent.Future

class UserRelativeServiceImpl extends UserRelativeService {

  override def saveEntity(entity: UserRelative): Future[Option[UserRelative]] =
    UserRelativeRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserRelative]] =
    UserRelativeRepository.roach.getEntities

  override def getEntity(userRelativeId: String): Future[Option[UserRelative]] =
    UserRelativeRepository.roach.getEntity(userRelativeId)

  override def deleteEntity(entity: UserRelative): Future[Boolean] =
    UserRelativeRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserRelativeRepository.roach.createTable

}

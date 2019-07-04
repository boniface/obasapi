package services.users.Impl.cockroachdb

import domain.users.UserApplicationResult
import services.users.UserApplicationResultService

import scala.concurrent.Future

class UserApplicationResultServiceImpl extends  UserApplicationResultService {

  override def saveEntity(entity: UserApplicationResult): Future[Boolean] =
    UserApplicationResultService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserApplicationResult]] =
    UserApplicationResultService.roach.getEntities

  override def getEntity(userApplicationResultId: String): Future[Option[UserApplicationResult]] =
    UserApplicationResultService.roach.getEntity(userApplicationResultId)

  override def deleteEntity(entity: UserApplicationResult): Future[Boolean] =
    UserApplicationResultService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserApplicationResultService.roach.createTable

}

package services.users.Impl.cockroachdb

import domain.users.UserApplicationResult
import repository.users.UserApplicationResultRepository
import services.users.UserApplicationResultService

import scala.concurrent.Future

class UserApplicationResultServiceImpl extends  UserApplicationResultService {

  override def saveEntity(entity: UserApplicationResult): Future[Boolean] =
    UserApplicationResultRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserApplicationResult]] =
    UserApplicationResultRepository.roach.getEntities

  override def getEntity(userApplicationResultId: String): Future[Option[UserApplicationResult]] =
    UserApplicationResultRepository.roach.getEntity(userApplicationResultId)

  override def deleteEntity(entity: UserApplicationResult): Future[Boolean] =
    UserApplicationResultRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserApplicationResultRepository.roach.createTable

}

package services.users.Impl

import domain.users.UserTown
import repository.users.UserTownRepository
import services.users.UserTownService

import scala.concurrent.Future

class UserTownServiceImpl extends UserTownService{
  override def saveEntity(entity: UserTown): Future[Option[UserTown]] =
    UserTownRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[UserTown]] =
    UserTownRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[UserTown]] =
    UserTownRepository.apply.getEntity(id)

  override def deleteEntity(entity: UserTown): Future[Boolean] =
    UserTownRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = UserTownRepository.apply.createTable
}

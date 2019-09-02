package services.users.Impl

import domain.users.UserPassword
import repository.users.UserPasswordRepository
import services.users.UserPasswordService

import scala.concurrent.Future

class UserPasswordServiceImpl extends UserPasswordService {

  override def saveEntity(entity: UserPassword): Future[Option[UserPassword]] =
    UserPasswordRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserPassword]] =
    UserPasswordRepository.roach.getEntities

  override def getEntity(userId: String): Future[Option[UserPassword]] =
    UserPasswordRepository.roach.getEntity(userId)

  override def deleteEntity(entity: UserPassword): Future[Boolean] =
    UserPasswordRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserPasswordRepository.roach.createTable

}

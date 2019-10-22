package services.users.Impl

import domain.login.ChangePassword
import repository.users.UserChangePasswordRepository
import services.users.UserChangePasswordService

import scala.concurrent.Future

class UserChangePasswordServiceImpl extends UserChangePasswordService {
  override def saveEntity(entity: ChangePassword): Future[Option[ChangePassword]] =
    UserChangePasswordRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[ChangePassword]] = UserChangePasswordRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[ChangePassword]] = UserChangePasswordRepository.apply.getEntity(id)

  override def deleteEntity(entity: ChangePassword): Future[Boolean] = ???

  override def createTable: Future[Boolean] = UserChangePasswordRepository.apply.createTable
}

package services.users.Impl.cockroachdb

import domain.users.UserPassword
import services.users.UserPasswordService

import scala.concurrent.Future

class UserPasswordServiceImpl extends UserPasswordService {

  override def saveEntity(entity: UserPassword): Future[Boolean] =
    UserPasswordService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserPassword]] =
    UserPasswordService.roach.getEntities

  override def getEntity(userAddressId: String): Future[Option[UserPassword]] =
    UserPasswordService.roach.getEntity(userAddressId)

  override def deleteEntity(entity: UserPassword): Future[Boolean] =
    UserPasswordService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserPasswordService.roach.createTable

}

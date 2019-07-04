package services.users.Impl.cockroachdb

import domain.users.UserContacts
import services.users.UserContactsService

import scala.concurrent.Future

class UserContactsServiceImpl extends UserContactsService{

  override def saveEntity(entity: UserContacts): Future[Boolean] =
    UserContactsService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserContacts]] =
    UserContactsService.roach.getEntities

  override def getEntity(userAddressId: String): Future[Option[UserContacts]] =
    UserContactsService.roach.getEntity(userAddressId)

  override def deleteEntity(entity: UserContacts): Future[Boolean] =
    UserContactsService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserContactsService.roach.createTable


}

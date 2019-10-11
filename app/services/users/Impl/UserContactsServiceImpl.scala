package services.users.Impl

import domain.users.UserContacts
import repository.users.UserContactsRepository
import services.users.UserContactsService

import scala.concurrent.Future

class UserContactsServiceImpl extends UserContactsService{

  override def saveEntity(entity: UserContacts): Future[Option[UserContacts]] =
    UserContactsRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserContacts]] =
    UserContactsRepository.roach.getEntities

  override def getEntity(userContactId: String): Future[Option[UserContacts]] = ???

  override def deleteEntity(entity: UserContacts): Future[Boolean] =
    UserContactsRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserContactsRepository.roach.createTable

  override def getEntityForUser(userId: String): Future[Seq[UserContacts]] =
    UserContactsRepository.roach.getEntityForUser(userId)

  override def getEntity(userId: String, contactTypeId: String): Future[Option[UserContacts]] =
    UserContactsRepository.roach.getEntity(userId, contactTypeId)
}

package repository.users.Impl.cockroachdb

import domain.users.UserContacts
import repository.users.UserContactsRepository

import scala.concurrent.Future

class UserContactsRepositoryImpl  extends UserContactsRepository{

  override def saveEntity(entity: UserContacts): Future[Boolean] = {
    UserContactsRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserContacts]] = {
    UserContactsRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserContacts]] = {
    UserContactsRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserContacts): Future[Boolean] = {
    UserContactsRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UserContactsRepository.roach.createTable
  }
}

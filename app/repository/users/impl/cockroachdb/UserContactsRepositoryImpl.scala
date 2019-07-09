package repository.users.impl.cockroachdb

import domain.users.UserContacts
import repository.users.UserContactsRepository
import repository.users.impl.cockroachdb.tables.UserContactsTable

import scala.concurrent.Future

class UserContactsRepositoryImpl  extends UserContactsRepository{

  override def saveEntity(entity: UserContacts): Future[Boolean] = {
    Future.successful(UserContactsTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserContacts]] = {
    UserContactsTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserContacts]] = {
    UserContactsTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserContacts): Future[Boolean] = {
    Future.successful(UserContactsTable.deleteEntity(entity.userContactId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserContactsTable.createTable)
  }
}

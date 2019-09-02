package repository.users.impl.cockroachdb

import domain.users.UserContacts
import repository.users.UserContactsRepository
import repository.users.impl.cockroachdb.tables.UserContactsTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserContactsRepositoryImpl  extends UserContactsRepository{

  override def saveEntity(entity: UserContacts): Future[Option[UserContacts]] = {
    UserContactsTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserContacts]] = {
    UserContactsTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserContacts]] = {
    UserContactsTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserContacts): Future[Boolean] = {
    UserContactsTable.deleteEntity(entity.userContactId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserContactsTable.createTable)
  }
}


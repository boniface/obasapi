package repository.users.impl.cockroachdb

import domain.users.UserContacts
import repository.users.UserContactsRepository
import repository.users.impl.cockroachdb.tables.{UserContactsTable, UserContactsTableCreate}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserContactsRepositoryImpl  extends UserContactsRepository{

  override def saveEntity(entity: UserContacts): Future[Option[UserContacts]] = {
    UserContactsTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserContacts]] = {
    UserContactsTable.getEntities
  }

  override def getEntity(userId: String, contactTypeId: String): Future[Option[UserContacts]] = {
    UserContactsTable.getEntity(userId, contactTypeId)
  }

  override def getEntityForUser(userId: String): Future[Seq[UserContacts]] = {
    UserContactsTable.getEntityForUser(userId)
  }

  override def deleteEntity(entity: UserContacts): Future[Boolean] = {
    UserContactsTable.deleteEntity(entity.userId, entity.contactTypeId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserContactsTableCreate.createTable)
  }

  override def getEntity(id: String): Future[Option[UserContacts]] = ???
}


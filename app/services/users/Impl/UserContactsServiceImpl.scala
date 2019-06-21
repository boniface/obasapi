package services.users.Impl

import domain.users.UserContacts
import services.users.UserContactsService

import scala.concurrent.Future

class UserContactsServiceImpl extends UserContactsService{

  override def saveEntity(entity: UserContacts): Future[Boolean] = ???

  override def getEntities: Future[Seq[UserContacts]] = ???

  override def getEntity(id: String): Future[Option[UserContacts]] = ???

  override def deleteEntity(entity: UserContacts): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???


}

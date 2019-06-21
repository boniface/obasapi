package services.users.Impl

import domain.users.UserDocuments
import services.users.UserDocumentsService

import scala.concurrent.Future

class UserDocumentsServiceImpl extends UserDocumentsService {

  override def saveEntity(entity: UserDocuments): Future[Boolean] = ???

  override def getEntities: Future[Seq[UserDocuments]] = ???

  override def getEntity(id: String): Future[Option[UserDocuments]] = ???

  override def deleteEntity(entity: UserDocuments): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???

}

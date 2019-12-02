package services.users

import domain.users.UserDocument
import services.CrudService
import services.users.Impl.UserDocumentServiceImpl

import scala.concurrent.Future

trait UserDocumentService extends CrudService[UserDocument]{

  def getEntity(userId: String, documentId: String): Future[Option[UserDocument]]

  def getUserDocuments(userId: String): Future[Seq[UserDocument]]

}

object UserDocumentService{
  def roach: UserDocumentService = new UserDocumentServiceImpl()
}

package repository.users

import domain.users.UserDocument
import repository.Repository
import repository.users.impl.cockroachdb.UserDocumentRepositoryImpl

import scala.concurrent.Future

trait UserDocumentRepository extends Repository[UserDocument]{

  def getEntity(userId: String, documentId: String): Future[Option[UserDocument]]

  def getUserDocuments(userId: String): Future[Seq[UserDocument]]

}

object UserDocumentRepository{
  def roach: UserDocumentRepository = new UserDocumentRepositoryImpl()
}

package repository.users

import domain.users.UserContacts
import repository.Repository
import repository.users.impl.cockroachdb.UserContactsRepositoryImpl

import scala.concurrent.Future

trait UserContactsRepository extends Repository[UserContacts]{

  def getEntityForUser(id: String): Future[Seq[UserContacts]]

  def getEntity(userId: String, contactTypeId: String): Future[Option[UserContacts]]

}

object UserContactsRepository{
  def roach: UserContactsRepository = new UserContactsRepositoryImpl()
}

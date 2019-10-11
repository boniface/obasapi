package services.users

import domain.users.UserContacts
import services.CrudService
import services.users.Impl.UserContactsServiceImpl

import scala.concurrent.Future

trait UserContactsService extends CrudService[UserContacts]{

  def getEntityForUser(id: String): Future[Seq[UserContacts]]

  def getEntity(userId: String, contactTypeId: String): Future[Option[UserContacts]]

}

object UserContactsService{
  def roach: UserContactsService = new UserContactsServiceImpl()
}

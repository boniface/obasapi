package services.users

import domain.users.UserContacts
import services.CrudService
import services.users.Impl.cockroachdb

trait UserContactsService extends CrudService[UserContacts]{

}

object UserContactsService{
  def roach: UserContactsService = new cockroachdb.UserContactsServiceImpl()
}

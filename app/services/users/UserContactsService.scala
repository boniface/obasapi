package services.users

import domain.users.UserContacts
import services.CrudService
import services.users.Impl.UserContactsServiceImpl

trait UserContactsService extends CrudService[UserContacts]{

}

object UserContactsService{
  def apply: UserContactsService = new UserContactsServiceImpl()
}

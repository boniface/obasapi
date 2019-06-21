package services.users

import domain.users.UserCommunication
import services.CrudService
import services.users.Impl.UserCommunicationServiceImpl

trait UserCommunicationService extends CrudService[UserCommunication]{

}

object UserCommunicationService{
  def apply: UserCommunicationService = new UserCommunicationServiceImpl()
}
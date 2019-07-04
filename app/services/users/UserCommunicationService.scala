package services.users

import domain.users.UserCommunication
import services.CrudService
import services.users.Impl.cockroachdb

trait UserCommunicationService extends CrudService[UserCommunication]{

}

object UserCommunicationService{
  def roach: UserCommunicationService = new cockroachdb.UserCommunicationServiceImpl()
}
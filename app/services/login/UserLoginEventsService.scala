package services.login

import domain.login.UserLoginEvents
import services.CrudService
import services.login.Impl.UserLoginEventsServiceImpl

trait UserLoginEventsService extends CrudService[UserLoginEvents]{

}
object UserLoginEventsService{
  def apply: UserLoginEventsService = new UserLoginEventsServiceImpl()
}

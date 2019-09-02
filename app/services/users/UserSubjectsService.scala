package services.users

import domain.users.UserSubjects
import services.CrudService
import services.users.Impl.UserSubjectsServiceImpl

trait UserSubjectsService extends CrudService[UserSubjects]{

}

object UserSubjectsService{
  def roach: UserSubjectsService = new UserSubjectsServiceImpl()
}

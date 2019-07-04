package services.users

import domain.users.UserSubjects
import services.CrudService
import services.users.Impl.cockroachdb

trait UserSubjectsService extends CrudService[UserSubjects]{

}

object UserSubjectsService{
  def roach: UserSubjectsService = new cockroachdb.UserSubjectsServiceImpl()
}

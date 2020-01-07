package services.users

import domain.users.UserMatricSubject
import services.CrudService
import services.users.Impl.UserMatricSubjectServiceImpl

import scala.concurrent.Future

trait UserMatricSubjectService extends CrudService[UserMatricSubject] {
  def getEntity(userId: String, subjectId: String): Future[Option[UserMatricSubject]]
  def getEntitiesForUser(userId: String): Future[Seq[UserMatricSubject]]
}

object UserMatricSubjectService {
  def apply: UserMatricSubjectService = new UserMatricSubjectServiceImpl()
}

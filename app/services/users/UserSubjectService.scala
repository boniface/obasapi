package services.users

import domain.users.UserSubject
import services.CrudService
import services.users.Impl.UserSubjectServiceImpl

import scala.concurrent.Future

trait UserSubjectService extends CrudService[UserSubject]{
  def getEntity(userId: String, institutionId: String, subjectId: String): Future[Option[UserSubject]]
  def getEntitiesForUser(userId: String): Future[Seq[UserSubject]]
  def getEntitiesForUserPerInstitution(userId: String, institutionId: String): Future[Seq[UserSubject]]
}

object UserSubjectService{
  def roach: UserSubjectService = new UserSubjectServiceImpl()
}

package services.users

import domain.users.UserTertiarySubject
import services.CrudService
import services.users.Impl.UserTertiarySubjectServiceImpl

import scala.concurrent.Future

trait UserTertiarySubjectService extends CrudService[UserTertiarySubject] {
  def getEntity(userId: String, applicationId: String, subjectId: String): Future[Option[UserTertiarySubject]]
  def getEntitiesForApplication(userId: String, applicationId: String): Future[Seq[UserTertiarySubject]]
  def getEntitiesForUser(userId: String): Future[Seq[UserTertiarySubject]]
  def deleteEntitiesForApplication(userId: String, applicationId: String): Future[Boolean]
}

object UserTertiarySubjectService {
  def apply: UserTertiarySubjectService = new UserTertiarySubjectServiceImpl()
}

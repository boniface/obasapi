package repository.users

import domain.users.UserTertiarySubject
import repository.Repository
import repository.users.impl.cockroachdb.UserTertiarySubjectRepositoryImpl

import scala.concurrent.Future

trait UserTertiarySubjectRepository extends Repository[UserTertiarySubject] {
  def getEntity(userId: String, applicationId: String, subjectId: String): Future[Option[UserTertiarySubject]]
  def getEntitiesForApplication(userId: String, applicationId: String): Future[Seq[UserTertiarySubject]]
  def getEntitiesForUser(userId: String): Future[Seq[UserTertiarySubject]]
  def deleteEntitiesForApplication(userId: String, applicationId: String): Future[Boolean]
}

object UserTertiarySubjectRepository {
  def apply: UserTertiarySubjectRepository = new UserTertiarySubjectRepositoryImpl()
}

package repository.users

import domain.users.UserSubject
import repository.Repository
import repository.users.impl.cockroachdb.UserSubjectRepositoryImpl

import scala.concurrent.Future

trait UserSubjectRepository extends Repository[UserSubject] {
  def getEntity(userId: String, institutionId: String, subjectId: String): Future[Option[UserSubject]]
  def getEntitiesForUser(userId: String): Future[Seq[UserSubject]]
  def getEntitiesForUserPerInstitution(userId: String, institutionId: String): Future[Seq[UserSubject]]
}

object UserSubjectRepository{
  def roach: UserSubjectRepository = new UserSubjectRepositoryImpl()
}

package repository.users

import domain.users.UserMatricSubject
import repository.Repository
import repository.users.impl.cockroachdb.UserMatricSubjectRepositoryImpl

import scala.concurrent.Future

trait UserMatricSubjectRepository extends Repository[UserMatricSubject] {
  def getEntity(userId: String, subjectId: String): Future[Option[UserMatricSubject]]
  def getEntitiesForUser(userId: String): Future[Seq[UserMatricSubject]]
}

object UserMatricSubjectRepository {
  def apply: UserMatricSubjectRepository = new UserMatricSubjectRepositoryImpl()
}

package repository.users

import domain.users.UserApplication
import repository.Repository
import repository.users.impl.cockroachdb.UserApplicationRepositoryImpl

import scala.concurrent.Future

trait UserApplicationRepository extends Repository[UserApplication]{
  def getEntity(id: String, applicationId: String): Future[Option[UserApplication]]
  def getEntitiesForUser(id: String): Future[Seq[UserApplication]]
}

object UserApplicationRepository{
  def roach: UserApplicationRepository = new UserApplicationRepositoryImpl()
}

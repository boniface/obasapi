package repository.users

import domain.users.UserApplicationStatus
import repository.Repository
import repository.users.impl.cockroachdb.UserApplicationStatusRepositoryImpl

import scala.concurrent.Future

trait UserApplicationStatusRepository extends Repository[UserApplicationStatus]{

  def getEntity(id: String, applicationId: String): Future[Option[UserApplicationStatus]]
  def getEntityForApplication(id: String): Future[Seq[UserApplicationStatus]]


}
object UserApplicationStatusRepository {
  def roach: UserApplicationStatusRepository =new UserApplicationStatusRepositoryImpl()
}

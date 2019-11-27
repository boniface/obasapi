package repository.login

import domain.login.UserLoginEvents
import repository.Repository

import scala.concurrent.Future

trait UserLoginEventsRepository extends Repository[UserLoginEvents] {
  def getUserLoginEvent(email: String, id: String): Future[Option[UserLoginEvents]]
  def getUserLoginEvents(email: String): Future[Seq[UserLoginEvents]]
}

object UserLoginEventsRepository {
  def apply: UserLoginEventsRepository = new UserLoginEventsRepositoryImpl()
}


package repository.users

import domain.users.UserCommunication
import repository.Repository
import repository.users.impl.cockroachdb

trait UserCommunicationRepository extends Repository[UserCommunication]{

}

object UserCommunicationRepository{
  def roach: UserCommunicationRepository = new cockroachdb.UserCommunicationRepositoryImpl()
}

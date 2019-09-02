package repository.users

import domain.users.UserCommunication
import repository.Repository
import repository.users.impl.cockroachdb.UserCommunicationRepositoryImpl

trait UserCommunicationRepository extends Repository[UserCommunication]{

}

object UserCommunicationRepository{
  def roach: UserCommunicationRepository = new UserCommunicationRepositoryImpl()
}

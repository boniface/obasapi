package repository.users

import domain.users.UserCommunication
import repository.Repository
import repository.users.Impl.cassandra.UserCommunicationRepositoryImpl

trait UserCommunicationRepository extends Repository[UserCommunication]{

}

object UserCommunicationRepository{
  def apply: UserCommunicationRepository = new UserCommunicationRepositoryImpl()
}

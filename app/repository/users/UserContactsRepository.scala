package repository.users

import domain.users.UserContacts
import repository.Repository
import repository.users.Impl.cassandra.UserContactsRepositoryImpl

trait UserContactsRepository extends Repository[UserContacts]{

}

object UserContactsRepository{
  def apply: UserContactsRepository = new UserContactsRepositoryImpl()
}

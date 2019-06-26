package repository.users

import domain.users.UserAddress
import repository.Repository
import repository.users.Impl.cassandra.UserAddressRepositoryImpl

trait UserAddressRepository extends Repository[UserAddress]{

}

object UserAddressRepository{
  def apply: UserAddressRepository = new UserAddressRepositoryImpl()
}

package repository.users

import domain.users.UserAddress
import repository.Repository
import repository.users.Impl.cockroachdb

trait UserAddressRepository extends Repository[UserAddress]{

}

object UserAddressRepository{
  def roach: UserAddressRepository = new cockroachdb.UserAddressRepositoryImpl()
}

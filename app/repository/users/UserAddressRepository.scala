package repository.users

import domain.users.UserAddress
import repository.Repository
import repository.users.impl.cockroachdb

trait UserAddressRepository extends Repository[UserAddress]{

}

object UserAddressRepository{
  def roach: UserAddressRepository = new cockroachdb.UserAddressRepositoryImpl()
}

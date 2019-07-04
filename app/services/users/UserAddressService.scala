package services.users

import domain.users.UserAddress
import services.CrudService
import services.users.Impl.cockroachdb

trait UserAddressService extends CrudService[UserAddress]{

}

object UserAddressService{
  def roach: UserAddressService = new cockroachdb.UserAddressServiceImpl()
}
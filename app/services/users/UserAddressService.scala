package services.users

import domain.users.UserAddress
import services.CrudService
import services.users.Impl.UserAddressServiceImpl

trait UserAddressService extends CrudService[UserAddress]{

}

object UserAddressService{
  def apply: UserAddressService = new UserAddressServiceImpl()
}
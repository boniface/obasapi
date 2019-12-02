package services.users

import domain.users.UserAddress
import services.CrudService
import services.users.Impl.UserAddressServiceImpl

import scala.concurrent.Future

trait UserAddressService extends CrudService[UserAddress]{

  def getEntity(userId: String, addressTypeId: String): Future[Option[UserAddress]]

  def getEntityForUser(userId: String): Future[Seq[UserAddress]]

}

object UserAddressService{
  def apply: UserAddressService = new UserAddressServiceImpl()
}
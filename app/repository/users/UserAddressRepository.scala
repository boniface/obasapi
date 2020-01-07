package repository.users

import domain.users.UserAddress
import repository.Repository
import repository.users.impl.cockroachdb.UserAddressRepositoryImpl

import scala.concurrent.Future

trait UserAddressRepository extends Repository[UserAddress]{

  def getEntity(id: String, addressTypeId: String): Future[Option[UserAddress]]

  def getEntityForUser(id: String): Future[Seq[UserAddress]]

}

object UserAddressRepository{
  def roach: UserAddressRepository = new UserAddressRepositoryImpl()
}

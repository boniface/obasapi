package services.users

import domain.users.UserAddress
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserAddressServiceTest extends FunSuite{
  val entity = UserAddress("1","13 Bratton Way","7141")
  val repository = UserAddressService
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.userAddressId), 2 minutes)
    assert(result.head.userAddressId==entity.userAddressId)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(physicalAddress = "14 Loop Street")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userAddressId), 2 minutes)
    assert(result.head.physicalAddress==updatedEntity.physicalAddress)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userAddressId), 2 minutes)
    assert(result.isEmpty)

  }
}

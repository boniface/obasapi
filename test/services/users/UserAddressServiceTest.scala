package services.users

import domain.users.UserAddress
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserAddressServiceTest extends FunSuite{
  val entity = UserAddress("1","13 Bratton Way","7141")
  val roachService = UserAddressService.roach
  test("createEntity"){
    val result = Await.result(roachService.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(roachService.getEntity(entity.userAddressId), 2 minutes)
    assert(result.head.userAddressId==entity.userAddressId)
  }

  test("createEntities"){
    val result = Await.result(roachService.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(physicalAddress = "14 Loop Street")
    Await.result(roachService.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(roachService.getEntity(entity.userAddressId), 2 minutes)
    assert(result.head.physicalAddress==updatedEntity.physicalAddress)
  }


  test("deleteEntities"){
    Await.result(roachService.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.getEntity(entity.userAddressId), 2 minutes)
    assert(result.isEmpty)

  }
}

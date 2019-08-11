package services.users

import domain.users.UserAddress
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserAddressServiceTest extends FunSuite{
  val entity = UserAddress("1","13 Bratton Way","7141")
  val roachService = UserAddressService.apply
  test("createEntity"){
    val result = Await.result(roachService.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

//  test("readEntity"){
//    val result = Await.result(roachService.getEntity(entity.userAddressId), 2 minutes)
//    println(result)
//    assert(result.head.userAddressId==entity.userAddressId)
//  }
//
//  test("getEntities") {
//    val result = Await.result(roachService.getEntities, 2 minutes)
//    println(result)
//    assert(result.nonEmpty)
//  }
//
  test("updateEntity") {
    val result = Await.result(roachService.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }
//
//
//  test("deleteEntities"){
//    Await.result(roachService.deleteEntity(entity), 2 minutes)
//    val result = Await.result(roachService.getEntity(entity.userAddressId), 2 minutes)
//    assert(result.isEmpty)
//
//  }
}

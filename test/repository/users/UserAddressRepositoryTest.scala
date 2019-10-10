package repository.users

import domain.users.UserAddress
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._


class UserAddressRepositoryTest extends FunSuite{
  val entity = UserAddress("1", "1", "13 asd Me Way","605")
  val repository = UserAddressRepository

  test("createEntity") {
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)
  }

  test("readEntity") {
    val result = Await.result(repository.roach.getEntity(entity.userId, entity.addressTypeId), 2 minutes)
    assert(result.head.userId == entity.userId)
  }

  test("readEntityForUser") {
    val result = Await.result(repository.roach.getEntity(entity.userId), 2 minutes)
    println(result)
    assert(result.head.userId == entity.userId)
  }

  test("getEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val updatedEntity = entity.copy(address = "14 Loop Street")
    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.userId, entity.addressTypeId), 2 minutes)
    assert(result.head.address == updatedEntity.address)
  }

  test("deleteEntity") {
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.userId, entity.addressTypeId), 2 minutes)
    assert(result.isEmpty)
  }
}

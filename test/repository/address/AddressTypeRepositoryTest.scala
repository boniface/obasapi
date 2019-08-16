package repository.address


import domain.address.AddressType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class AddressTypeRepositoryTest extends FunSuite {

  val entity = AddressType("5","9 Browning RD")
  val repository = AddressTypeRepository
  
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.addressTypeID), 2 minutes)
    assert(result.head.addressTypeID==entity.addressTypeID)
  }

  test("getEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.addressTypeID), 2 minutes)
    assert(result.isEmpty)
  }
  
}

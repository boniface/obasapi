package repository.address


import domain.address.AddressType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class AddressTypeRepositoryTest extends FunSuite {

  val entity = AddressType("1","6 Browning RD")
  val repository = AddressTypeRepository
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.addressTypeID), 2 minutes)
    assert(result.head.addressTypeID==entity.addressTypeID)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(addressName = "6 Browning RD")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.addressTypeID), 2 minutes)
    assert(result.head.addressName==updatedEntity.addressName)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.addressTypeID), 2 minutes)
    assert(result.isEmpty)

  }
  
}

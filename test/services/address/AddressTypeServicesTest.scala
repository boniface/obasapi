package services.address

import domain.address.AddressType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class AddressTypeServicesTest extends FunSuite {
  val entity = AddressType("1","81258")
  val service = AddressTypeService
  test("createEntity"){
    val result = Await.result(service.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(service.apply.getEntity(entity.addressTypeID), 2 minutes)
    assert(result.head.addressTypeID==entity.addressTypeID)
  }

  test("createEntities"){
    val result = Await.result(service.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(addressName = "58248")
    Await.result(service.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(service.apply.getEntity(entity.addressTypeID), 2 minutes)
    assert(result.head.addressName==updatedEntity.addressName)
  }


  test("deleteEntities"){
    Await.result(service.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.apply.getEntity(entity.addressTypeID), 2 minutes)
    assert(result.isEmpty)

  }

}

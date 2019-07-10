package repository.address


import domain.address.AddressType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class AddressTypeRepositoryTest extends FunSuite {

  val entity = AddressType("5","9 Browning RD")
  val repository = AddressTypeRepository


//  test("readEntity"){
//    val result = Await.result(repository.roach.getEntity(entity.addressTypeID), 2 minutes)
//    assert(result.head.addressTypeID==entity.addressTypeID)
//  }
//
//  test("getEntities"){
//    val result = Await.result(repository.roach.getEntities, 2 minutes)
//    println(result)
//    assert(result.nonEmpty)
//  }
//
//  test("updateEntities"){
//    val updatedEntity=entity.copy(addressName = "6 Browning RD")
//    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
//    val result = Await.result(repository.roach.getEntity(entity.addressTypeID), 2 minutes)
//    assert(result.head.addressName==updatedEntity.addressName)
//  }


//  test("deleteEntities"){
//    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
//    val result = Await.result(repository.roach.getEntity(entity.addressTypeID), 2 minutes)
//    assert(result.isEmpty)
//  }
  
}

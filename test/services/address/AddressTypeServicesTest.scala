package services.address

import domain.address.AddressType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class AddressTypeServicesTest extends FunSuite {

  val entity = AddressType("5", "81258")
  val service = AddressTypeService
  test("createEntity") {
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

//  test("readEntity"){
//    val result = Await.result(service.roach.getEntity(entity.addressTypeID), 2 minutes)
//    print(result)
//    assert(result.head.addressTypeID==entity.addressTypeID)
//  }
//
//
//  test("readEntity") {
//    val result = Await.result(service.roach.getEntity(entity.addressTypeID), 2 minutes)
//    print(result)
//    assert(result.head.addressTypeID == entity.addressTypeID)
//  }
//
//  test("getEntities") {
//    val result = Await.result(service.roach.getEntities, 2 minutes)
//    println(result)
//    assert(result.nonEmpty)
//  }
//
  test("updateEntity") {
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities") {
    Await.result(service.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.addressTypeID), 2 minutes)
    assert(result.isEmpty)

  }

}

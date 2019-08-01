package services.address


import domain.address.ContactType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class ContactTypeServicesTest extends FunSuite {

  val entity = ContactType("1","81258")
  val service = ContactTypeService
  test("createEntity"){
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

//  test("readEntity"){
//    val result = Await.result(service.roach.getEntity(entity.contactTypeId), 2 minutes)
//    assert(result.head.contactTypeId==entity.contactTypeId)
//  }
//
//  test("getEntities") {
//    val result = Await.result(service.roach.getEntities, 72 minutes)
//    assert(result.nonEmpty)
//  }
//
//  test("updateEntities"){
//    val updatedEntity=entity.copy(name = "58248")
//    Await.result(service.roach.saveEntity(updatedEntity), 2 minutes)
//    val result = Await.result(service.roach.getEntity(entity.contactTypeId), 2 minutes)
//    assert(result.head.name==updatedEntity.name)
//  }
//
//
//  test("deleteEntities"){
//    Await.result(service.roach.deleteEntity(entity), 2 minutes)
//    val result = Await.result(service.roach.getEntity(entity.contactTypeId), 2 minutes)
//    assert(result.isEmpty)
//
//  }


}

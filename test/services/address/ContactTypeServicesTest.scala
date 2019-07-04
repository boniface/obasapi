package services.address


import domain.address.ContactType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class ContactTypeServicesTest extends FunSuite {

  val entity = ContactType("1","81258")
  val service = ContactTypeService
  test("createEntity"){
    val result = Await.result(service.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(service.apply.getEntity(entity.contactTypeId), 2 minutes)
    assert(result.head.contactTypeId==entity.contactTypeId)
  }

  test("createEntities"){
    val result = Await.result(service.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(name = "58248")
    Await.result(service.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(service.apply.getEntity(entity.contactTypeId), 2 minutes)
    assert(result.head.name==updatedEntity.name)
  }


  test("deleteEntities"){
    Await.result(service.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.apply.getEntity(entity.contactTypeId), 2 minutes)
    assert(result.isEmpty)

  }


}

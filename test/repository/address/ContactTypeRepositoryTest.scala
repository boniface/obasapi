package repository.address

import domain.address.ContactType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class ContactTypeRepositoryTest extends FunSuite {

  val entity = ContactType("415","81258")
  val repository = ContactTypeRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

//  test("readEntity"){
//    val result = Await.result(repository.roach.getEntity(entity.contactTypeId), 2 minutes)
//    assert(result.head.contactTypeId==entity.contactTypeId)
//  }
//
//  test("getEntities"){
//    val result = Await.result(repository.roach.getEntities, 2 minutes)
//      println(result)
//    assert(result.nonEmpty)
//  }
//
  test("updateEntity") {
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }

//
//  test("deleteEntities"){
//    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
//    val result = Await.result(repository.roach.getEntity(entity.contactTypeId), 2 minutes)
//    assert(result.isEmpty)
//
//  }
  

}

package repository.address

import domain.address.ContactType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class ContactTypeRepositoryTest extends FunSuite {

  val entity = ContactType("1","81258")
  val repository = ContactTypeRepository
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.contactTypeId), 2 minutes)
    assert(result.head.contactTypeId==entity.contactTypeId)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(name = "58248")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.contactTypeId), 2 minutes)
    assert(result.head.name==updatedEntity.name)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.contactTypeId), 2 minutes)
    assert(result.isEmpty)

  }
  

}

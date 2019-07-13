package repository.demographics

import domain.demographics.Gender
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class GenderRepositoryTest extends FunSuite {

  val entity = Gender("1","Male")
  val repository = GenderRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.genderId), 2 minutes)
    assert(result.head.genderId==entity.genderId)
  }

  test("getEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(genderName = "Female")
    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.genderId), 2 minutes)
    assert(result.head.genderName==updatedEntity.genderName)
  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.genderId), 2 minutes)
    assert(result.isEmpty)
  }

}

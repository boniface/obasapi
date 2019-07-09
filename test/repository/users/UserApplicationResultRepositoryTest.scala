package repository.users

import domain.users.UserApplicationResult
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._


class UserApplicationResultRepositoryTest extends FunSuite{
  val entity = UserApplicationResult("1","It's nice")
  val repository = UserApplicationResultRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.userApplicationResultId), 2 minutes)
    assert(result.head.userApplicationResultId==entity.userApplicationResultId)
  }

  test("createEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(description = "It's very nice")
    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.userApplicationResultId), 2 minutes)
    assert(result.head.description==updatedEntity.description)
  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.userApplicationResultId), 2 minutes)
    assert(result.isEmpty)

  }
}
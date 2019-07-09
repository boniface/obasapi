package repository.users

import domain.users.UserResults
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._


class UserResultsRepositoryTest extends FunSuite{
  val entity = UserResults("1","failed")
  val repository = UserResultsRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.userResultsId), 2 minutes)
    assert(result.head.userResultsId==entity.userResultsId)
  }

  test("createEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(description = "passed")
    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.userResultsId), 2 minutes)
    assert(result.head.description==updatedEntity.description)
  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.userResultsId), 2 minutes)
    assert(result.isEmpty)

  }
}
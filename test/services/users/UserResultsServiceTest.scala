package services.users

import domain.users.UserResults
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserResultsServiceTest extends FunSuite{
  val entity = UserResults("1","failed")
  val roachService = UserResultsService
  test("createEntity"){
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(roachService.roach.getEntity(entity.userResultsId), 2 minutes)
    assert(result.head.userResultsId==entity.userResultsId)
  }

  test("createEntities"){
    val result = Await.result(roachService.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(description = "passed")
    Await.result(roachService.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.userResultsId), 2 minutes)
    assert(result.head.description==updatedEntity.description)
  }


  test("deleteEntities"){
    Await.result(roachService.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.userResultsId), 2 minutes)
    assert(result.isEmpty)

  }
}
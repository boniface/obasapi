package services.users

import domain.users.UserResults
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserResultsServiceTest extends FunSuite{
  val entity = UserResults("2","failed")
  val roachService = UserResultsService
  test("createEntity"){
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

    test("readEntity"){
      val result = Await.result(roachService.roach.getEntity(entity.userResultsId), 2 minutes)
      assert(result.head.userResultsId==entity.userResultsId)
    }

    test("getEntities"){
      val result = Await.result(roachService.roach.getEntities, 2 minutes)
      assert(result.nonEmpty)
    }

  test("updateEntity") {
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


    test("deleteEntities"){
      Await.result(roachService.roach.deleteEntity(entity), 2 minutes)
      val result = Await.result(roachService.roach.getEntity(entity.userResultsId), 2 minutes)
      assert(result.isEmpty)

    }
}
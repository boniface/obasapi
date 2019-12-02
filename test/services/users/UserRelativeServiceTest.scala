package services.users

import domain.users.UserRelative
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserRelativeServiceTest extends FunSuite{
  val entity = UserRelative("1","AJ","12345","single","aj@yahoo.com")
  val roachService = UserRelativeService
  test("createEntity"){
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(roachService.roach.getEntity(entity.userId), 2 minutes)
    assert(result.head.userId==entity.userId)
  }

  test("getEntities") {
    val result = Await.result(roachService.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(roachService.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.userId), 2 minutes)
    assert(result.isEmpty)

  }
}
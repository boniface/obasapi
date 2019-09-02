package services.users

import domain.users.UserPassword
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserPasswordServiceTest extends FunSuite{
  val entity = UserPassword("1","password")
  val roachService = UserPasswordService
  test("createEntity"){
    val result = Await.result(roachService.apply.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(roachService.apply.getEntity(entity.email), 2 minutes)
    assert(result.head.email==entity.email)
  }

  test("getEntities") {
    val result = Await.result(roachService.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(roachService.apply.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(roachService.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.apply.getEntity(entity.email), 2 minutes)
    assert(result.isEmpty)

  }
}
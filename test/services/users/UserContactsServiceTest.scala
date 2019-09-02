package services.users

import domain.users.UserContacts
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserContactsServiceTest extends FunSuite{
  val entity = UserContacts("1","1234567890", "0987654321", "aj@yahoo.com")
  val roachService = UserContactsService
  test("createEntity"){
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(roachService.roach.getEntity(entity.userContactId), 2 minutes)
    assert(result.head.userContactId==entity.userContactId)
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
    val result = Await.result(roachService.roach.getEntity(entity.userContactId), 2 minutes)
    assert(result.isEmpty)

  }
}
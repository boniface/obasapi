package services.users

import domain.users.UserContacts
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserContactsServiceTest extends FunSuite {

  val entity = UserContacts("12", "23", "test@test.com")
  val roachService = UserContactsService
  test("createEntity") {
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity") {
    val result = Await.result(roachService.roach.getEntity(entity.userId, entity.contactTypeId), 2 minutes)
    println(result)
    assert(result.head.userId == entity.userId)
  }

  test("readEntityForUser") {
    val result = Await.result(roachService.roach.getEntityForUser(entity.userId), 2 minutes)
    println(result)
    assert(result.head.userId == entity.userId)
  }

  test("getEntities") {
    val result = Await.result(roachService.roach.getEntities, 2 minutes)
    println(result)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val updated = entity.copy(contact = "update@contact.com")
    val result = Await.result(roachService.roach.saveEntity(updated), 2 minutes)
    println(result)
    assert(result.isEmpty)

  }


  test("deleteEntities") {
    Await.result(roachService.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.userId, entity.contactTypeId), 2 minutes)
    println(result)
    assert(result.isEmpty)

  }
}
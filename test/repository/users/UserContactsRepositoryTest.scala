package repository.users

import domain.users.UserContacts
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._


class UserContactsRepositoryTest extends FunSuite {
  val entity = UserContacts("12", "23", "test@test.com")
  val entity2 = UserContacts("12", "32", "0987654321")

  val repository = UserContactsRepository

  test("createEntity") {
    val result = Await.result(repository.roach.saveEntity(entity2), 2 minutes)
    assert(result.nonEmpty)
  }

  test("readEntity") {
    val result = Await.result(repository.roach.getEntity(entity.userId, entity.contactTypeId), 2 minutes)
    println(result)
    assert(result.head.userId == entity.userId)
  }

  test("readEntitiesForUser") {
    val result = Await.result(repository.roach.getEntityForUser(entity.userId), 2 minutes)
    println(result)
    assert(result.head.userId == entity.userId)
  }

  test("getEntities") {
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    println(result)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val updated = entity.copy(contact = "new_test@test.com")
    val result = Await.result(repository.roach.saveEntity(updated), 2 minutes)
    println(result)
    assert(result.isEmpty)

  }


  test("deleteEntities") {
    Await.result(repository.roach.deleteEntity(entity2), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity2.userId, entity2.contactTypeId), 2 minutes)
    println(result)
    assert(result.isEmpty)

  }
}
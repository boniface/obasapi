package repository.users

import java.time.{LocalDate, LocalDateTime}

import domain.users.User
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._


class UserRepositoryTest extends FunSuite{
  val entity = User("abrahamj.wiese@gmail.com","Abraha","Jabobus","Wiesee", LocalDateTime.now())
  val repository = UserRepository
  test("createEntity") {
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity") {
    val result = Await.result(repository.roach.getEntity(entity.email), 2 minutes)
    assert(result.head.email == entity.email)
  }

  test("getEntities") {
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities") {
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.email), 2 minutes)
    assert(result.isEmpty)

  }
}

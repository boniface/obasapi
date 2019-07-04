package services.users

import java.time.LocalDateTime

import domain.users.User
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserServiceTest extends FunSuite{
  val entity = User("ajwiese@gmail.com","Abraham","Jabobus","Wiese", LocalDateTime.of(1989,1,6,1600,32,44))
  val repository = UserService
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.email), 2 minutes)
    assert(result.head.email==entity.email)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(firstName = "JvR High")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.email), 2 minutes)
    assert(result.head.firstName==updatedEntity.firstName)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.email), 2 minutes)
    assert(result.isEmpty)

  }
}
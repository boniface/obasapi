package repository.users

import java.time.LocalDate

import domain.users.User
import org.scalatest.FunSuite
import repository.users.UserRepository

import scala.concurrent.Await
import scala.concurrent.duration._


class UserRepositoryTest extends FunSuite{
  val entity = User("ajwiese@gmail.com","Abraham","Jabobus","Wiese", LocalDate.now)
  val repository = UserRepository
//  test("createEntity"){
//    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
//    assert(result)
//
//  }

//  test("readEntity"){
//    val result = Await.result(repository.roach.getEntity(entity.email), 2 minutes)
//    assert(result.head.email==entity.email)
//  }
//
  test("createEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }
//
//  test("updateEntities"){
//    val updatedEntity=entity.copy(firstName = "JvR High")
//    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
//    val result = Await.result(repository.roach.getEntity(entity.email), 2 minutes)
//    assert(result.head.firstName==updatedEntity.firstName)
//  }
//
//
//  test("deleteEntities"){
//    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
//    val result = Await.result(repository.roach.getEntity(entity.email), 2 minutes)
//    assert(result.isEmpty)
//
//  }
}

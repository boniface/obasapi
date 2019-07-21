package repository.users

import domain.users.UserPassword
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._


class UserPasswordRepositoryTest extends FunSuite{
  val entity = UserPassword("1","password")
  val repository = UserPasswordRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.email), 2 minutes)
    assert(result.head.email==entity.email)
  }

  test("getEntities") {
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(password = "P@ssw0rd")
    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.email), 2 minutes)
    assert(result.head.password==updatedEntity.password)
  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.email), 2 minutes)
    assert(result.isEmpty)

  }
}
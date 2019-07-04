package repositories.users

import domain.users.UserPassword
import org.scalatest.FunSuite
import repository.users.UserPasswordRepository

import scala.concurrent.Await
import scala.concurrent.duration._


class UserPasswordRepositoryTest extends FunSuite{
  val entity = UserPassword("1","password")
  val repository = UserPasswordRepository
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.userPasswordId), 2 minutes)
    assert(result.head.userPasswordId==entity.userPasswordId)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(password = "P@ssw0rd")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userPasswordId), 2 minutes)
    assert(result.head.password==updatedEntity.password)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userPasswordId), 2 minutes)
    assert(result.isEmpty)

  }
}
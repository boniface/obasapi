package repositories.users

import domain.users.UserRole
import org.scalatest.FunSuite
import repository.users.UserRoleRepository

import scala.concurrent.Await
import scala.concurrent.duration._


class UserRoleRepositoryTest extends FunSuite{
  val entity = UserRole("1","aj","desc test")
  val repository = UserRoleRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.userRoleId), 2 minutes)
    assert(result.head.userRoleId==entity.userRoleId)
  }

  test("createEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(description = "desc tested")
    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.userRoleId), 2 minutes)
    assert(result.head.description==updatedEntity.description)
  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.userRoleId), 2 minutes)
    assert(result.isEmpty)

  }
}
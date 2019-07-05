package repository.users

import domain.users.UserRelative
import org.scalatest.FunSuite
import repository.users.UserRelativeRepository

import scala.concurrent.Await
import scala.concurrent.duration._


class UserRelativeRepositoryTest extends FunSuite{
  val entity = UserRelative("1","AJ","12345","single","aj@yahoo.com")
  val repository = UserRelativeRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.userRelativeId), 2 minutes)
    assert(result.head.userRelativeId==entity.userRelativeId)
  }

  test("createEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(relationship = "not single")
    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.userRelativeId), 2 minutes)
    assert(result.head.relationship==updatedEntity.relationship)
  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.userRelativeId), 2 minutes)
    assert(result.isEmpty)

  }
}
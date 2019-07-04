package services.users

import domain.users.UserRelative
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserRelativeServiceTest extends FunSuite{
  val entity = UserRelative("1","AJ","12345","single","aj@yahoo.com")
  val repository = UserRelativeService
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.userRelativeId), 2 minutes)
    assert(result.head.userRelativeId==entity.userRelativeId)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(relationship = "not single")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userRelativeId), 2 minutes)
    assert(result.head.relationship==updatedEntity.relationship)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userRelativeId), 2 minutes)
    assert(result.isEmpty)

  }
}
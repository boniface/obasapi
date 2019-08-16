package repository.users

import domain.users.UserInstitution
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._


class UserInstitutionRepositoryTest extends FunSuite{
  val entity = UserInstitution("2","JvR")
  val repository = UserInstitutionRepository
  test("createEntity") {
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity") {
    val result = Await.result(repository.roach.getEntity(entity.userInstitutionId), 2 minutes)
    assert(result.head.userInstitutionId == entity.userInstitutionId)
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
    val result = Await.result(repository.roach.getEntity(entity.userInstitutionId), 2 minutes)
    assert(result.isEmpty)

  }
}
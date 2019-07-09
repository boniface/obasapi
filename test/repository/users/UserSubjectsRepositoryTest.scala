package repository.users

import domain.users.UserSubjects
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._


class UserSubjectsRepositoryTest extends FunSuite{
  val entity = UserSubjects("1","aj","maths","two")
  val repository = UserSubjectsRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.userSubjectId), 2 minutes)
    assert(result.head.userSubjectId==entity.userSubjectId)
  }

  test("createEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(description = "science")
    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.userSubjectId), 2 minutes)
    assert(result.head.description==updatedEntity.description)
  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.userSubjectId), 2 minutes)
    assert(result.isEmpty)

  }
}
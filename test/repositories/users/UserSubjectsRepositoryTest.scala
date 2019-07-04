package repositories.users

import domain.users.UserSubjects
import org.scalatest.FunSuite
import repository.users.UserSubjectsRepository

import scala.concurrent.Await
import scala.concurrent.duration._


class UserSubjectsRepositoryTest extends FunSuite{
  val entity = UserSubjects("1","aj","maths","two")
  val repository = UserSubjectsRepository
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.userSubjectId), 2 minutes)
    assert(result.head.userSubjectId==entity.userSubjectId)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(description = "science")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userSubjectId), 2 minutes)
    assert(result.head.description==updatedEntity.description)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userSubjectId), 2 minutes)
    assert(result.isEmpty)

  }
}
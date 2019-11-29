package repository.users

import domain.users.UserSubjects
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._


class UserSubjectRepositoryTest extends FunSuite{
  val entity = UserSubjects("13","aj","maths","two")
  val repository = UserSubjectRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.userSubjectId), 2 minutes)
    assert(result.head.userSubjectId==entity.userSubjectId)
  }

  test("getEntities") {
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.userSubjectId), 2 minutes)
    assert(result.isEmpty)

  }
}
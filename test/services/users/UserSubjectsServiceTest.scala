package services.users

import domain.users.UserSubjects
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserSubjectsServiceTest extends FunSuite{
  val entity = UserSubjects("1","aj","maths","two")
  val roachService = UserSubjectsService
  test("createEntity"){
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(roachService.roach.getEntity(entity.userSubjectId), 2 minutes)
    assert(result.head.userSubjectId==entity.userSubjectId)
  }

  test("getEntities") {
    val result = Await.result(roachService.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(description = "science")
    Await.result(roachService.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.userSubjectId), 2 minutes)
    assert(result.head.description==updatedEntity.description)
  }


  test("deleteEntities"){
    Await.result(roachService.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.userSubjectId), 2 minutes)
    assert(result.isEmpty)

  }
}
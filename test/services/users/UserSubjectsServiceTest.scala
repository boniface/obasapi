package services.users

import domain.users.UserSubjects
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserSubjectsServiceTest extends FunSuite{
  val entity = UserSubjects("5","israel","maths","two")
  val roachService = UserSubjectsService
  test("createEntity"){
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(roachService.roach.getEntity(entity.userSubjectId), 2 minutes)
    assert(result.head.userSubjectId==entity.userSubjectId)
  }

  test("getEntities") {
    val result = Await.result(roachService.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(roachService.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.userSubjectId), 2 minutes)
    assert(result.isEmpty)

  }
}
package services.users

import domain.users.UserInstitution
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserInstitutionServiceTest extends FunSuite{
  val entity = UserInstitution("1","JvR")
  val roachService = UserInstitutionService
  test("createEntity"){
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(roachService.roach.getEntity(entity.institutionId), 2 minutes)
    assert(result.head.institutionId==entity.institutionId)
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
    val result = Await.result(roachService.roach.getEntity(entity.institutionId), 2 minutes)
    assert(result.isEmpty)

  }
}
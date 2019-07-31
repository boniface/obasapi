package services.users

import domain.users.UserRole
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserRoleServiceTest extends FunSuite{
  val entity = UserRole("1","rol23")
  val roachService = UserRoleService
  test("createEntity"){
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

//  test("readEntity"){
//    val result = Await.result(roachService.roach.getEntity(entity.userId), 2 minutes)
//    assert(result.head.userId==entity.userId)
//  }
//
//  test("getEntities") {
//    val result = Await.result(roachService.roach.getEntities, 2 minutes)
//    assert(result.nonEmpty)
//  }
//
//  test("updateEntities"){
//    val updatedEntity=entity.copy(roleId = "desc tested")
//    Await.result(roachService.roach.saveEntity(updatedEntity), 2 minutes)
//    val result = Await.result(roachService.roach.getEntity(entity.userId), 2 minutes)
//    assert(result.head.roleId==updatedEntity.roleId)
//  }
//
//
//  test("deleteEntities"){
//    Await.result(roachService.roach.deleteEntity(entity), 2 minutes)
//    val result = Await.result(roachService.roach.getEntity(entity.userId), 2 minutes)
//    assert(result.isEmpty)
//
//  }
}
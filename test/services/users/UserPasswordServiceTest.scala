package services.users

import domain.users.UserPassword
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserPasswordServiceTest extends FunSuite{
  val entity = UserPassword("1","password")
  val roachService = UserPasswordService
  test("createEntity"){
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(roachService.roach.getEntity(entity.userPasswordId), 2 minutes)
    assert(result.head.userPasswordId==entity.userPasswordId)
  }

  test("createEntities"){
    val result = Await.result(roachService.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(password = "P@ssw0rd")
    Await.result(roachService.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.userPasswordId), 2 minutes)
    assert(result.head.password==updatedEntity.password)
  }


  test("deleteEntities"){
    Await.result(roachService.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.userPasswordId), 2 minutes)
    assert(result.isEmpty)

  }
}
package services.users

import domain.users.UserDocuments
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserDocumentsServiceTest extends FunSuite{
  val entity = UserDocuments("1","12")
  val roachService = UserDocumentsService
  test("createEntity"){
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(roachService.roach.getEntity(entity.userDocumentsId), 2 minutes)
    assert(result.head.userDocumentsId==entity.userDocumentsId)
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
    val result = Await.result(roachService.roach.getEntity(entity.userDocumentsId), 2 minutes)
    assert(result.isEmpty)

  }
}
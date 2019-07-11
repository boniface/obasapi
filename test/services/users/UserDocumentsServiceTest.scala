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
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(roachService.roach.getEntity(entity.userDocumentsId), 2 minutes)
    assert(result.head.userDocumentsId==entity.userDocumentsId)
  }

  test("getEntities") {
    val result = Await.result(roachService.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(documentId = "13")
    Await.result(roachService.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.userDocumentsId), 2 minutes)
    assert(result.head.documentId==updatedEntity.documentId)
  }


  test("deleteEntities"){
    Await.result(roachService.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.userDocumentsId), 2 minutes)
    assert(result.isEmpty)

  }
}
package repository.users

import domain.users.UserDocuments
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._


class UserDocumentsRepositoryTest extends FunSuite{
  val entity = UserDocuments("1","12")
  val repository = UserDocumentsRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.userDocumentsId), 2 minutes)
    assert(result.head.userDocumentsId==entity.userDocumentsId)
  }

  test("createEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(documentId = "13")
    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.userDocumentsId), 2 minutes)
    assert(result.head.documentId==updatedEntity.documentId)
  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.userDocumentsId), 2 minutes)
    assert(result.isEmpty)

  }
}
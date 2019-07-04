package repositories.users

import domain.users.UserDocuments
import org.scalatest.FunSuite
import repository.users.UserDocumentsRepository

import scala.concurrent.Await
import scala.concurrent.duration._


class UserDocumentsRepositoryTest extends FunSuite{
  val entity = UserDocuments("1","12")
  val repository = UserDocumentsRepository
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.userDocumentsId), 2 minutes)
    assert(result.head.userDocumentsId==entity.userDocumentsId)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(documentId = "13")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userDocumentsId), 2 minutes)
    assert(result.head.documentId==updatedEntity.documentId)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userDocumentsId), 2 minutes)
    assert(result.isEmpty)

  }
}
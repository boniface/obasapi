package repository.documents

import domain.documents.DocumentType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class DocumentTypeRepositoryTest extends FunSuite {

  val entity = DocumentType("1","6 Browning RD")
  val repository = DocumentTypeRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.documentTypeId), 2 minutes)
    assert(result.head.documentTypeId==entity.documentTypeId)
  }

  test("createEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(documentTypename = "6 Browning RD")
    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.documentTypeId), 2 minutes)
    assert(result.head.documentTypename==updatedEntity.documentTypename)
  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.documentTypeId), 2 minutes)
    assert(result.isEmpty)
  }
  
}

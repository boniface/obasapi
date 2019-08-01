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
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.documentTypeId), 2 minutes)
    assert(result.head.documentTypeId==entity.documentTypeId)
  }

  test("getEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.documentTypeId), 2 minutes)
    assert(result.isEmpty)
  }
  
}

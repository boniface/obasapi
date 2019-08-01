package services.documents

import domain.documents.DocumentType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class DocumentTypeServicesTest extends FunSuite {

  val entity = DocumentType("123","Metric Certificate")
  val service = DocumentTypeService
  test("createEntity"){
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(service.roach.getEntity(entity.documentTypeId), 2 minutes)
    assert(result.head.documentTypeId==entity.documentTypeId)
  }

  test("readEntities") {
    val result = Await.result(service.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(service.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.documentTypeId), 2 minutes)
    assert(result.isEmpty)

  }
  
}

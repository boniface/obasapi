package services.documents

import java.time.LocalDateTime

import domain.documents.Document
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class DocumentServiceTest extends FunSuite {

  val entity = Document("215038142@mycput.ac.za","879542","DT4587","Affidavity proof address ","url","mime",LocalDateTime.of(2001,3,5,2300,23,2),"yes")
  val service = DocumentService
  test("createEntity"){
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(service.roach.getEntity(entity.email), 2 minutes)
    assert(result.head.email==entity.email)
  }

  test("createEntities"){
    val result = Await.result(service.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(documentsId = "Female")
    Await.result(service.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.email), 2 minutes)
    assert(result.head.documentsId==updatedEntity.documentsId)
  }


  test("deleteEntities"){
    Await.result(service.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.email), 2 minutes)
    assert(result.isEmpty)

  }

}

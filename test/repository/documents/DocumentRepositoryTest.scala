package repository.documents

import java.time.LocalDateTime

import domain.documents.Document
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class DocumentRepositoryTest extends FunSuite {

  val entity = Document("879542","DT4587","Affidavity proof address ","url","mime",LocalDateTime.of(2001,3,5,2300,23,2),"yes", "")
  val repository = DocumentRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.documentId), 2 minutes)
    assert(result.head.documentId==entity.documentId)
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
    val result = Await.result(repository.roach.getEntity(entity.documentId), 2 minutes)
    assert(result.isEmpty)
  }
  
}

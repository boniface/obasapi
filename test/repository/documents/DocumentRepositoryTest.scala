package repository.documents

import java.time.LocalDateTime

import domain.documents.Document
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class DocumentRepositoryTest extends FunSuite {

  val entity = Document("215038142@mycput.ac.za","879542","DT4587","Affidavity proof address ","url","mime",LocalDateTime.of(2001,3,5,2300,23,2),"yes")
  val repository = DocumentRepository
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.email), 2 minutes)
    assert(result.head.email==entity.email)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(documentsId = "874658")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.email), 2 minutes)
    assert(result.head.documentsId==updatedEntity.documentsId)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.email), 2 minutes)
    assert(result.isEmpty)
  }
  
}

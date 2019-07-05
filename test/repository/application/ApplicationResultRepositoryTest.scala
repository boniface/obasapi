package repository.application

import domain.application.ApplicationResult
import org.scalatest.FunSuite
import java.time.LocalDateTime
import scala.concurrent.Await
import scala.concurrent.duration._


class ApplicationResultRepositoryTest extends FunSuite {

  val entity =ApplicationResult("1","Bachelor Acquired",LocalDateTime.of(2000,5,6,1500,15,5))
  val repository = ApplicationResultRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity. applicationResultId), 2 minutes)
    assert(result.head. applicationResultId==entity. applicationResultId)
  }

  test("createEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(description = "6 Browning RD")
    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity. applicationResultId), 2 minutes)
    assert(result.head.description==updatedEntity.description)
  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity. applicationResultId), 2 minutes)
    assert(result.isEmpty)

  }


}

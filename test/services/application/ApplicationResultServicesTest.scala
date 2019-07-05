package services.application

import java.time.LocalDateTime

import domain.application.ApplicationResult
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class ApplicationResultServicesTest extends FunSuite {

  val entity = ApplicationResult("1","Bachelor Acquired",LocalDateTime.of(2000,5,6,1500,15,5))
  val service = ApplicationResultService
  test("createEntity"){
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(service.roach.getEntity(entity.applicationResultId), 2 minutes)
    assert(result.head.applicationResultId==entity.applicationResultId)
  }

  test("createEntities"){
    val result = Await.result(service.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(description = "Diploma Acquired")
    Await.result(service.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.applicationResultId), 2 minutes)
    assert(result.head.description==updatedEntity.description)
  }


  test("deleteEntities"){
    Await.result(service.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.applicationResultId), 2 minutes)
    assert(result.isEmpty)

  }
  
  
}

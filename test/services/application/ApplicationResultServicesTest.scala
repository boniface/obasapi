package services.application

import java.time.LocalDateTime

import domain.application.ApplicationResult
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class ApplicationResultServicesTest extends FunSuite {

  val entity = ApplicationResult("1", "Bachelor Acquired", LocalDateTime.of(2000, 5, 6, 12, 15, 5))
  val service = ApplicationResultService

  test("createEntity"){
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(service.roach.getEntity(entity.applicationResultId), 2 minutes)
    assert(result.head.applicationResultId==entity.applicationResultId)
  }


  test("getEntities") {
    val result = Await.result(service.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(service.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.applicationResultId), 2 minutes)
    assert(result.isEmpty)

  }
  
  
}

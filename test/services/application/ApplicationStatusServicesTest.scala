package services.application

import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
import java.time.LocalDateTime

import domain.application.ApplicationStatus

class ApplicationStatusServicesTest extends FunSuite {

  val entity = ApplicationStatus("1", "Pending ", LocalDateTime.of(2000, 5, 6, 22, 15, 5))
  val service = ApplicationStatusService
  test("createEntity"){
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(service.roach.getEntity(entity.applicationStatusId), 2 minutes)
    assert(result.head.applicationStatusId==entity.applicationStatusId)
  }

  test("getEntities") {
    val result = Await.result(service.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(description = "Processed ")
    Await.result(service.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.applicationStatusId), 2 minutes)
    assert(result.head.description==updatedEntity.description)
  }


  test("deleteEntities"){
    Await.result(service.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.applicationStatusId), 2 minutes)
    assert(result.isEmpty)

  }
  
  
}

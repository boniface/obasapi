package services.application

import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
import java.time.LocalDateTime

import domain.application.ApplicationStatus

class ApplicationStatusServicesTest extends FunSuite {

  val entity = ApplicationStatus("1","Pending ",LocalDateTime.of(2000,5,6,1500,15,5))
  val service = ApplicationStatusService
  test("createEntity"){
    val result = Await.result(service.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(service.apply.getEntity(entity.applicationStatusId), 2 minutes)
    assert(result.head.applicationStatusId==entity.applicationStatusId)
  }

  test("createEntities"){
    val result = Await.result(service.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(description = "Processed ")
    Await.result(service.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(service.apply.getEntity(entity.applicationStatusId), 2 minutes)
    assert(result.head.description==updatedEntity.description)
  }


  test("deleteEntities"){
    Await.result(service.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.apply.getEntity(entity.applicationStatusId), 2 minutes)
    assert(result.isEmpty)

  }
  
  
}

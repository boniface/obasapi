package services.application

import domain.application.ApplicantType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class ApplicantTypeServicesTest extends FunSuite {

  val entity = ApplicantType("1","81258")
  val service = ApplicantTypeService
  test("createEntity"){
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(service.roach.getEntity(entity.applicantTypeId), 2 minutes)
    assert(result.head.applicantTypeId==entity.applicantTypeId)
  }

  test("createEntities"){
    val result = Await.result(service.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(name = "546932")
    Await.result(service.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.applicantTypeId), 2 minutes)
    assert(result.head.name==updatedEntity.name)
  }


  test("deleteEntities"){
    Await.result(service.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.applicantTypeId), 2 minutes)
    assert(result.isEmpty)

  }
  
}

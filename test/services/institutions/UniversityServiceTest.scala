package services.institutions

import domain.institutions.University
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UniversityServiceTest extends FunSuite{
  val entity = University("1","CPUT","13 Test Street","Western Cape")
  val roachService = UniversityService
  test("createEntity"){
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(roachService.roach.getEntity(entity.universityId), 2 minutes)
    assert(result.head.universityId==entity.universityId)
  }

  test("getEntities") {
    val result = Await.result(roachService.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(universityName = "CPUT Cape Town")
    Await.result(roachService.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.universityId), 2 minutes)
    assert(result.head.universityName==updatedEntity.universityName)
  }


  test("deleteEntities"){
    Await.result(roachService.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.universityId), 2 minutes)
    assert(result.isEmpty)

  }
}

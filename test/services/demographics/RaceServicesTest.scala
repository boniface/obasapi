package services.demographics

import domain.demographics.Race
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class RaceServicesTest extends FunSuite {

  val entity = Race("48","Black")
  val service = RaceService
  test("createEntity"){
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(service.roach.getEntity(entity.raceId), 2 minutes)
    assert(result.head.raceId==entity.raceId)
  }

  test("getEntities"){
    val result = Await.result(service.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(raceName = "African")
    Await.result(service.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.raceId), 2 minutes)
    assert(result.head.raceName==updatedEntity.raceName)
  }


  test("deleteEntities"){
    Await.result(service.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.raceId), 2 minutes)
    assert(result.isEmpty)

  }

}

package services.location

import domain.location.LocationType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class LocationTypeServiceTest extends FunSuite{
  val entity = LocationType("1","Lion's Head")
  val roachService = LocationTypeService
  test("createEntity"){
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(roachService.roach.getEntity(entity.locationTypeId), 2 minutes)
    assert(result.head.locationTypeId==entity.locationTypeId)
  }

  test("getEntities"){
    val result = Await.result(roachService.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(name = "JvR High")
    Await.result(roachService.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.locationTypeId), 2 minutes)
    assert(result.head.name==updatedEntity.name)
  }


  test("deleteEntities"){
    Await.result(roachService.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.locationTypeId), 2 minutes)
    assert(result.isEmpty)

  }
}
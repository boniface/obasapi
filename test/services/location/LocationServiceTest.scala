package services.location

import domain.location.Location
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class LocationServiceTest extends FunSuite{
  val entity = Location("1","Cape Town","33.9249","18.4241","12345","Western Cape")
  val roachService = LocationService
  test("createEntity"){
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(roachService.roach.getEntity(entity.locationId), 2 minutes)
    assert(result.head.locationId==entity.locationId)
  }

  test("createEntities"){
    val result = Await.result(roachService.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(code = "123456")
    Await.result(roachService.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.locationId), 2 minutes)
    assert(result.head.code==updatedEntity.code)
  }


  test("deleteEntities"){
    Await.result(roachService.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.locationId), 2 minutes)
    assert(result.isEmpty)

  }
}

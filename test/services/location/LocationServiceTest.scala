package services.location

import domain.location.Location
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class LocationServiceTest extends FunSuite{
  val entity = Location("3", "Cape Town", "33.9249", "18.4241", "12345", "Western Cape", Some("1"))
  val roachService = LocationService

  // column "PARENT_ID" does not exist
  test("createEntity"){
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

//  test("readEntity"){
//    val result = Await.result(roachService.roach.getEntity(entity.locationId), 2 minutes)
//    assert(result.head.locationId==entity.locationId)
//  }
//
//  test("getEntities") {
//    val result = Await.result(roachService.roach.getEntities, 2 minutes)
//    assert(result.nonEmpty)
//  }
//
  test("updateEntity") {
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }
//
//
//  test("deleteEntities"){
//    Await.result(roachService.roach.deleteEntity(entity), 2 minutes)
//    val result = Await.result(roachService.roach.getEntity(entity.locationId), 2 minutes)
//    assert(result.isEmpty)
//
//  }
}

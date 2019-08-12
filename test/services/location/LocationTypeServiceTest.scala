package services.location

import domain.location.LocationType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class LocationTypeServiceTest extends FunSuite{
  val entity = LocationType("1","Lion's Head","111")
  val roachService = LocationTypeService
  test("createEntity"){
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

//  test("readEntity"){
//    val result = Await.result(roachService.roach.getEntity(entity.locationTypeId), 2 minutes)
//    assert(result.head.locationTypeId==entity.locationTypeId)
//  }
//
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
//    val result = Await.result(roachService.roach.getEntity(entity.locationTypeId), 2 minutes)
//    assert(result.isEmpty)
//
//  }
}
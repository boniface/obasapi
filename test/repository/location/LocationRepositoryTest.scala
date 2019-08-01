package repository.location

import cats.syntax.option
import domain.location.Location
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class LocationRepositoryTest extends FunSuite{
  val entity = Location("16","Cape Town","33.9249","18.4241","12345","Western Cape",Some("asdsad"))
  val repository = LocationRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

//  test("readEntity"){
//    val result = Await.result(repository.roach.getEntity(entity.locationId), 2 minutes)
//    assert(result.head.locationId==entity.locationId)
//  }
//
//
//  test("getEntities") {
//    val result = Await.result(repository.roach.getEntities, 2 minutes)
//    assert(result.nonEmpty)
//  }
//
  test("updateEntity") {
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }
//
//
//  test("deleteEntities"){
//    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
//    val result = Await.result(repository.roach.getEntity(entity.locationId), 2 minutes)
//    assert(result.isEmpty)
//
//  }
}

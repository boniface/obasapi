package repository.location

import domain.location.LocationType
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._


class LocationTypeRepositoryTest extends FunSuite{
  val entity = LocationType("3","Lion's Head","111")
  val repository = LocationTypeRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }
//
//  test("readEntity"){
//    val result = Await.result(repository.roach.getEntity(entity.locationTypeId), 2 minutes)
//    assert(result.head.locationTypeId==entity.locationTypeId)
//  }


//  test("getEntities") {
//    val result = Await.result(repository.roach.getEntities, 2 minutes)
//    assert(result.nonEmpty)
//  }

//  test("updateEntities"){
//    val updatedEntity=entity.copy(name = "JvR High")
//    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
//    val result = Await.result(repository.roach.getEntity(entity.locationTypeId), 2 minutes)
//    assert(result.head.name==updatedEntity.name)
//  }
//
//
//  test("deleteEntities"){
//    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
//    val result = Await.result(repository.roach.getEntity(entity.locationTypeId), 2 minutes)
//    assert(result.isEmpty)
//
//  }
}

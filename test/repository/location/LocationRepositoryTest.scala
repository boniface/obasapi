package repository.location

import domain.location.Location
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class LocationRepositoryTest extends FunSuite{
  val entity = Location("1","Cape Town","33.9249","18.4241","12345","Western Cape",Some("1"))
  val repository = LocationRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.locationId), 2 minutes)
    assert(result.head.locationId==entity.locationId)
  }

  test("getEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(code = "123456")
    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.locationId), 2 minutes)
    assert(result.head.code==updatedEntity.code)
  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.locationId), 2 minutes)
    assert(result.isEmpty)

  }
}

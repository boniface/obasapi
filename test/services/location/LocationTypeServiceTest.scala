package services.location

import domain.location.LocationType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class LocationTypeServiceTest extends FunSuite{
  val entity = LocationType("1","Lion's Head")
  val repository = LocationTypeService
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.locationTypeId), 2 minutes)
    assert(result.head.locationTypeId==entity.locationTypeId)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(name = "JvR High")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.locationTypeId), 2 minutes)
    assert(result.head.name==updatedEntity.name)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.locationTypeId), 2 minutes)
    assert(result.isEmpty)

  }
}
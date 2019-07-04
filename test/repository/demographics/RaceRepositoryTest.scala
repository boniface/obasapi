package repository.demographics

import domain.demographics.Race
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class RaceRepositoryTest extends FunSuite {

  val entity = Race("1","6 Browning RD")
  val repository = RaceRepository
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.raceId), 2 minutes)
    assert(result.head.raceId==entity.raceId)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(raceName = "6 Browning RD")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.raceId), 2 minutes)
    assert(result.head.raceName==updatedEntity.raceName)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.raceId), 2 minutes)
    assert(result.isEmpty)
  }


}

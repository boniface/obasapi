package repository.demographics

import domain.demographics.Title
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class TitleRepositoryTest extends FunSuite {

  val entity = Title("90","Mr")
  val repository = TitleRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.titleId), 2 minutes)
    assert(result.head.titleId==entity.titleId)
  }

  test("getEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(titlename = "Miss")
    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.titleId), 2 minutes)
    assert(result.head.titlename==updatedEntity.titlename)
  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.titleId), 2 minutes)
    assert(result.isEmpty)

  }
  
}

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
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.titleId), 2 minutes)
    assert(result.head.titleId==entity.titleId)
  }

  test("getEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.titleId), 2 minutes)
    assert(result.isEmpty)

  }
  
}

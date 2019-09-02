package services.demographics

import domain.demographics.Title
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class TitleServicesTest extends FunSuite {

  val entity = Title("1","Mr")
  val service = TitleService
  test("createEntity"){
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(service.roach.getEntity(entity.titleId), 2 minutes)
    assert(result.head.titleId==entity.titleId)
  }


  test("getEntities") {
    val result = Await.result(service.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(service.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.titleId), 2 minutes)
    assert(result.isEmpty)

  }

}

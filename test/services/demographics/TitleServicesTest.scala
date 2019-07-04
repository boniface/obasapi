package services.demographics

import domain.demographics.Title
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class TitleServicesTest extends FunSuite {

  val entity = Title("1","Mr")
  val service = TitleService
  test("createEntity"){
    val result = Await.result(service.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(service.apply.getEntity(entity.titleId), 2 minutes)
    assert(result.head.titleId==entity.titleId)
  }

  test("createEntities"){
    val result = Await.result(service.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(titlename = "Mr")
    Await.result(service.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(service.apply.getEntity(entity.titleId), 2 minutes)
    assert(result.head.titlename==updatedEntity.titlename)
  }


  test("deleteEntities"){
    Await.result(service.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.apply.getEntity(entity.titleId), 2 minutes)
    assert(result.isEmpty)

  }

}

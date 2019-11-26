package repository.academics.impl.cockcapplydb

import domain.academics.Subject
import org.scalatest.FunSuite
import services.academics.SubjectService

import scala.concurrent.Await
import scala.concurrent.duration._

class SubjectRepositoryImplTest extends FunSuite {


  val entity = Subject("S01","ITS",null)
  val applyService = SubjectService
  test("createEntity"){
    val result = Await.result(applyService.apply.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(applyService.apply.getEntity(entity.id), 2 minutes)
    assert(result.head.id==entity.id)
  }

  test("getEntities") {
    val result = Await.result(applyService.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(applyService.apply.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(applyService.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(applyService.apply.getEntity(entity.id), 2 minutes)
    assert(result.isEmpty)

  }


}

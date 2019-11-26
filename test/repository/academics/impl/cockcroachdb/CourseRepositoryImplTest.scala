package repository.academics.impl.cockcapplydb

import domain.academics.Course
import org.scalatest.FunSuite
import services.academics.CourseService

import scala.concurrent.Await
import scala.concurrent.duration._

class CourseRepositoryImplTest extends FunSuite {

  val entity =  Course("C01","Information Technology",null)
  val roachService =  CourseService
  test("createEntity"){
    val result = Await.result(roachService.apply.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(roachService.apply.getEntity(entity.id), 2 minutes)
    assert(result.head.id==entity.id)
  }

  test("getEntities") {
    val result = Await.result(roachService.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(roachService.apply.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(roachService.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.apply.getEntity(entity.id), 2 minutes)
    assert(result.isEmpty)

  }

}

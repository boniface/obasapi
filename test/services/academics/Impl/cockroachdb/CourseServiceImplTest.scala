package services.academics.Impl.cockroachdb

import domain.academics.Course
import org.scalatest.FunSuite
import services.academics.CourseService

import scala.concurrent.Await
import scala.concurrent.duration._

class CourseServiceImplTest extends FunSuite {

  val entity = Course("c01","Information Technology",null)
  val roachService = CourseService.apply
  test("createEntity"){
    val result = Await.result(roachService.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(roachService.getEntity(entity.id), 2 minutes)

    assert(result.head.id==entity.id)
  }

 
  test("getEntities") {
    val result = Await.result(roachService.getEntities, 2 minutes)

    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val updatedEntity = entity.copy(courseName = "computer Science")
    val result = Await.result(roachService.saveEntity(updatedEntity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(roachService.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.getEntity(entity.id), 2 minutes)
    assert(result.isEmpty)

  }

}

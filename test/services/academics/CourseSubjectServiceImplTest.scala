package services.academics

import domain.academics.CourseSubject
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._

class CourseSubjectServiceImplTest extends FunSuite {

  val entity = CourseSubject("c01","s01")
  val roachService = CourseSubjectService

  test("createEntity"){
    val result = Await.result(roachService.apply.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(roachService.apply.getEntity(entity.courseId, entity.subjectId), 2 minutes)
    assert(result.head.courseId==entity.courseId)
  }

  test("readEntitiesForCourse") {
    val result = Await.result(roachService.apply.getEntitiesForCourse(entity.courseId), 2 minutes)
    println(result)
    assert(result.head.courseId == entity.courseId)
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
    val result = Await.result(roachService.apply.getEntity(entity.courseId), 2 minutes)
    assert(result.isEmpty)

  }

}

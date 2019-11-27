package services.institutions.Impl.cockroachdb

import domain.institutions.InstitutionCourse
import org.scalatest.FunSuite
import services.institutions.InstitutionCourseService

import scala.concurrent.Await
import scala.concurrent.duration._


class InstitutionCourseServiceImplTest extends FunSuite {

  val entity = InstitutionCourse("I01", "C01")
  val roachService = InstitutionCourseService.apply
  test("createEntity"){
    val result = Await.result(roachService.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(roachService.getEntity(entity.institutionId, entity.courseId), 2 minutes)

    assert(result.head.institutionId==entity.institutionId)
  }

  test("readEntityForUser") {
    val result = Await.result(roachService.getEntity(entity.institutionId), 2 minutes)
    println(result)
    assert(result.head.institutionId == entity.institutionId)
  }

  test("getEntities") {
    val result = Await.result(roachService.getEntities, 2 minutes)

    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val updatedEntity = entity.copy(courseId = "C05")
    val result = Await.result(roachService.saveEntity(updatedEntity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(roachService.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.getEntity(entity.institutionId), 2 minutes)
    assert(result.isEmpty)

  }

}

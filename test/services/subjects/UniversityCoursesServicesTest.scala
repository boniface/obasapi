package services.subjects

import domain.subjects.UniversityCourses
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class UniversityCoursesServicesTest extends FunSuite {

  val entity = UniversityCourses("1",null, "CPUT","Westen Cape", "Full Type")
  val service = UniversityCoursesService
  test("createEntity"){
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

test("readEntity"){
  val result = Await.result(service.roach.getEntity(entity.courseCode), 2 minutes)
  assert(result.head.courseCode==entity.courseCode)
}


  test("getEntities"){
    val result = Await.result(service.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }
  test("updateEntity") {
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
  Await.result(service.roach.deleteEntity(entity), 2 minutes)
  val result = Await.result(service.roach.getEntity(entity.courseCode), 2 minutes)
  assert(result.isEmpty)

}
  
}

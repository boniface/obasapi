package services.subjects

import domain.subjects.UniversityCourses
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class UniversityCoursesServicesTest extends FunSuite {

  val entity = UniversityCourses("1",null, "CPUT","Westen Cape", "Full Type")
  val service = UniversityCoursesService
  test("createEntity"){
    val result = Await.result(service.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(service.apply.getEntity(entity.courseCode), 2 minutes)
    assert(result.head.courseCode==entity.courseCode)
  }

  test("createEntities"){
    val result = Await.result(service.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(name = "UCT")
    Await.result(service.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(service.apply.getEntity(entity.courseCode), 2 minutes)
    assert(result.head.name==updatedEntity.name)
  }


  test("deleteEntities"){
    Await.result(service.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.apply.getEntity(entity.courseCode), 2 minutes)
    assert(result.isEmpty)

  }
  
}

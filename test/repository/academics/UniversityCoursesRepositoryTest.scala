package repository.academics

import domain.academics.UniversityCourses
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class UniversityCoursesRepositoryTest extends FunSuite {

  val entity = UniversityCourses("1",null,"CPUT","Westen Cape","Full Type")
  val repository = UniversityCoursesRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.courseCode), 2 minutes)
    assert(result.head.courseCode==entity.courseCode)
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
    val result = Await.result(repository.roach.getEntity(entity.courseCode), 2 minutes)
    assert(result.isEmpty)
  }
  
}

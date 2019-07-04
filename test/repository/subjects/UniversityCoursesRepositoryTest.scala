package repository.subjects

import domain.subjects.UniversityCourses
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class UniversityCoursesRepositoryTest extends FunSuite {

  val entity = UniversityCourses("1",null,"CPUT","Westen Cape","Full Type")
  val repository = UniversityCoursesRepository
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.courseCode), 2 minutes)
    assert(result.head.courseCode==entity.courseCode)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(name = "UCT")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.courseCode), 2 minutes)
    assert(result.head.name==updatedEntity.name)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.courseCode), 2 minutes)
    assert(result.isEmpty)
  }
  
}

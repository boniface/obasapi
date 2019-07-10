package services.subjects
import domain.subjects.MatricSubjects
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class MatricSubjectsServicesTest extends FunSuite {

  val entity = MatricSubjects("124",null,"Bachelor","Term 5 2020")
  val service = MatricSubjectsService
  test("createEntity"){
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(service.roach.getEntity(entity.subjectCode), 2 minutes)
    print(result)
    assert(result.head.subjectCode==entity.subjectCode)
  }

  test("getEntities"){
    val result = Await.result(service.roach.getEntities, 2 minutes)
    print(result)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(name = "Female")
    Await.result(service.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.subjectCode), 2 minutes)
    assert(result.head.name==updatedEntity.name)
  }


  test("deleteEntities"){
    Await.result(service.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.subjectCode), 2 minutes)
    assert(result.isEmpty)

  }

}

package repository.institutions

import domain.institutions.School
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._


class SchoolRepositoryTest extends FunSuite{
  val entity = School("1","JvR","13 Bree Street","Western Cape","012 458 2564")
  val repository = SchoolRepository

  test("createEntity") {

    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity") {
    val result = Await.result(repository.roach.getEntity(entity.schoolId), 2 minutes)
    assert(result.head.schoolId == entity.schoolId)
  }

  test("getEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    println(result)
    assert(result.nonEmpty)
  }
  test("updateEntity") {
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities") {
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.schoolId), 2 minutes)
    assert(result.isEmpty)

  }
}

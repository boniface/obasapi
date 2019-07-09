package repository.institutions

import domain.institutions.University
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._


class UniversityRepositoryTest extends FunSuite{
  val entity = University("1","CPUT","13 Test Street","Western Cape")
  val repository = UniversityRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.universityId), 2 minutes)
    assert(result.head.universityId==entity.universityId)
  }

  test("createEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(universityName = "CPUT Cape Town")
    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.universityId), 2 minutes)
    assert(result.head.universityName==updatedEntity.universityName)
  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.universityId), 2 minutes)
    assert(result.isEmpty)

  }
}

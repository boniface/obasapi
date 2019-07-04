package repositories.institutions

import domain.institutions.University
import org.scalatest.FunSuite
import repository.institutions.UniversityRepository

import scala.concurrent.Await
import scala.concurrent.duration._


class UniversityRepositoryTest extends FunSuite{
  val entity = University("1","CPUT","13 Test Street","Western Cape")
  val repository = UniversityRepository
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.universityId), 2 minutes)
    assert(result.head.universityId==entity.universityId)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(universityName = "CPUT Cape Town")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.universityId), 2 minutes)
    assert(result.head.universityName==updatedEntity.universityName)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.universityId), 2 minutes)
    assert(result.isEmpty)

  }
}

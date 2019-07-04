package repository.application

import domain.application.ApplicantType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class ApplicantTypeRepositoryTest extends FunSuite {

  val entity = ApplicantType("1","6 Browning RD")
  val repository = ApplicantTypeRepository
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.applicantTypeId), 2 minutes)
    assert(result.head.applicantTypeId==entity.applicantTypeId)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(name = "6 Browning RD")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.applicantTypeId), 2 minutes)
    assert(result.head.name==updatedEntity.name)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.applicantTypeId), 2 minutes)
    assert(result.isEmpty)

  }
  
}

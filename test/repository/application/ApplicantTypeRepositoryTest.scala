package repository.application

import domain.application.ApplicantType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class ApplicantTypeRepositoryTest extends FunSuite {

  val entity = ApplicantType("1","6 Browning RD")
  val repository = ApplicantTypeRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.applicantTypeId), 2 minutes)
    assert(result.head.applicantTypeId==entity.applicantTypeId)
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
    val result = Await.result(repository.roach.getEntity(entity.applicantTypeId), 2 minutes)
    assert(result.isEmpty)

  }
  
}

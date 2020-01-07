package repository.institutions

import domain.institutions.InstitutionLocation
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class InstitutionLocationRepositoryImplTest extends FunSuite {

  val entity = InstitutionLocation("5","9 Browning RD","N 352872","S 9827")
  val repository = InstitutionLocationRepository

  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.institutionId), 2 minutes)
    assert(result.head.institutionId==entity.institutionId)
  }

  test("getEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.institutionId), 2 minutes)
    assert(result.isEmpty)
  }


}

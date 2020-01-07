package services.institutions.Impl.cockroachdb

import domain.institutions.InstitutionLocation
import org.scalatest.FunSuite
import services.institutions.InstitutionLocationService

import scala.concurrent.Await
import scala.concurrent.duration._


class InstitutionLocationServiceImplTest extends FunSuite {

  val entity = InstitutionLocation("u01","101","N 323 233","S 763532")
  val roachService = InstitutionLocationService
  test("createEntity"){
    val result = Await.result(roachService.apply.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(roachService.apply.getEntity(entity.institutionId), 2 minutes)
    assert(result.head.institutionId==entity.institutionId)
  }

  test("getEntities") {
    val result = Await.result(roachService.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(roachService.apply.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(roachService.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.apply.getEntity(entity.institutionId), 2 minutes)
    assert(result.isEmpty)

  }

}

package services.institutions.Impl.cockroachdb

import domain.institutions.Institution
import org.scalatest.FunSuite
import services.institutions.InstitutionService

import scala.concurrent.Await
import scala.concurrent.duration._

class InstitutionServiceImplTest extends FunSuite {

  val entity = Institution("Id32322","UN323","CPUT")
  val roachService = InstitutionService
  test("createEntity"){
    val result = Await.result(roachService.apply.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(roachService.apply.getEntity(entity.id), 2 minutes)
    assert(result.head.id==entity.id)
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
    val result = Await.result(roachService.apply.getEntity(entity.id), 2 minutes)
    assert(result.isEmpty)

  }

}

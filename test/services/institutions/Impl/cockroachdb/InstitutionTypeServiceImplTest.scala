package services.institutions.Impl.cockroachdb

import domain.institutions.InstitutionType
import org.scalatest.FunSuite
import services.institutions.InstitutionTypeService

import scala.concurrent.Await
import scala.concurrent.duration._


class InstitutionTypeServiceImplTest extends FunSuite {

  val entity = InstitutionType("Id32322","CPUT",Option("University"))
  val roachService = InstitutionTypeService

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

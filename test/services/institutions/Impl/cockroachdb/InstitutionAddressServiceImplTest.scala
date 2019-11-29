package services.institutions.Impl.cockroachdb

import domain.institutions.InstitutionAddress
import org.scalatest.FunSuite
import services.institutions.InstitutionAddressService

import scala.concurrent.Await
import scala.concurrent.duration._


class InstitutionAddressServiceImplTest extends FunSuite {

  val entity = InstitutionAddress("I01", "AD23", "13 asd Me Way","605")
  val roachService = InstitutionAddressService.apply
  test("createEntity"){
    val result = Await.result(roachService.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(roachService.getEntity(entity.institutionId, entity.addressTypeId), 2 minutes)

    assert(result.head.institutionId==entity.institutionId)
  }

  test("readEntityForUser") {
    val result = Await.result(roachService.getEntity(entity.institutionId), 2 minutes)
    println(result)
    assert(result.head.institutionId == entity.institutionId)
  }

  test("getEntities") {
    val result = Await.result(roachService.getEntities, 2 minutes)

    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val updatedEntity = entity.copy(address = "14 Loop Street")
    val result = Await.result(roachService.saveEntity(updatedEntity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(roachService.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.getEntity(entity.institutionId), 2 minutes)
    assert(result.isEmpty)

  }

}

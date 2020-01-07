package services.institutions.Impl.cockroachdb

import domain.institutions.InstitutionContact
import org.scalatest.FunSuite
import services.institutions.InstitutionContactService

import scala.concurrent.Await
import scala.concurrent.duration._


class InstitutionContactServiceImplTest extends FunSuite {

  val entity = InstitutionContact("I01", "Tel", "021 555 3694")
  val roachService = InstitutionContactService.apply
  test("createEntity"){
    val result = Await.result(roachService.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(roachService.getEntity(entity.institutionId, entity.contactTypeId), 2 minutes)

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
    val updatedEntity = entity.copy(contact = "021 959 6707")
    val result = Await.result(roachService.saveEntity(updatedEntity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(roachService.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.getEntity(entity.institutionId), 2 minutes)
    assert(result.isEmpty)

  }

}

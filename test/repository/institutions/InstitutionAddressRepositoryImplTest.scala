package repository.institutions

import domain.institutions.InstitutionAddress
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class InstitutionAddressRepositoryImplTest extends FunSuite {

  val entity = InstitutionAddress("I01", "UNI20", "Sympol bellville ","930")
  val entity2 = InstitutionAddress("I01", "UNI20", "Zondeblom Cape Town ","1605")
  val repository = InstitutionAddressRepository

  test("createEntity") {
    println(entity)
    val result = Await.result(repository.apply.saveEntity(entity2), 2 minutes)
    assert(result.nonEmpty)
  }

  test("readEntity") {
    val result = Await.result(repository.apply.getEntity(entity.institutionId, entity.addressTypeId), 2 minutes)
    println(result.head)
    assert(result.head.institutionId == entity.institutionId)
  }

  test("readEntityForUser") {
    val result = Await.result(repository.apply.getEntitiesForInstitution(entity.institutionId), 2 minutes)
    println(result)
    assert(result.head.institutionId == entity.institutionId)
  }

  test("getEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    println(result)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val updatedEntity = entity.copy(address = "14 Loop Street")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.institutionId, entity.addressTypeId), 2 minutes)
    println(result)
    assert(result.head.address == updatedEntity.address)
  }

  test("deleteEntity") {
    Await.result(repository.apply.deleteEntity(entity2), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity2.institutionId, entity2.addressTypeId), 2 minutes)
    println(result)
    assert(result.isEmpty)
  }

}

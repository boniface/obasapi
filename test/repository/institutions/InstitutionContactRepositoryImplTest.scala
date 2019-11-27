package repository.institutions

import domain.institutions.InstitutionContact
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class InstitutionContactRepositoryImplTest extends FunSuite {

  val entity = InstitutionContact("I01", "Tell", "021 959 6707")
  val entity2 = InstitutionContact("I01", "Cell", "073 1121 123 ")
  val repository = InstitutionContactRepository

  test("createEntity") {
    println(entity)
    val result = Await.result(repository.apply.saveEntity(entity2), 2 minutes)
    assert(result.nonEmpty)
  }

  test("readEntity") {
    val result = Await.result(repository.apply.getEntity(entity.institutionId, entity.contactTypeId), 2 minutes)
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
    val updatedEntity = entity.copy(contact = "089 456 2584")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.institutionId, entity.contactTypeId), 2 minutes)
    println(result)
    assert(result.head.contact == updatedEntity.contact)
  }

  test("deleteEntity") {
    Await.result(repository.apply.deleteEntity(entity2), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity2.institutionId, entity2.contactTypeId), 2 minutes)
    println(result)
    assert(result.isEmpty)
  }


}

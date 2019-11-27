package repository.institutions

import domain.institutions.InstitutionCourse
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class InstitutionCourseRepositoryImplTest extends FunSuite {

  val entity = InstitutionCourse("I01", "C01")
  val entity2 = InstitutionCourse("I01", "C01")
  val repository = InstitutionCourseRepository

  test("createEntity") {
    println(entity)
    val result = Await.result(repository.apply.saveEntity(entity2), 2 minutes)
    assert(result.nonEmpty)
  }

  test("readEntity") {
    val result = Await.result(repository.apply.getEntity(entity.institutionId, entity.courseId), 2 minutes)
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
    val updatedEntity = entity.copy(courseId = "C02")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.institutionId, entity.courseId), 2 minutes)
    println(result)
    assert(result.head.courseId == updatedEntity.courseId)
  }

  test("deleteEntity") {
    Await.result(repository.apply.deleteEntity(entity2), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity2.institutionId, entity2.courseId), 2 minutes)
    println(result)
    assert(result.isEmpty)
  }

}

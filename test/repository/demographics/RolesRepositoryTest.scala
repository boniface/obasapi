package repository.demographics

import domain.demographics.Roles
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class RolesRepositoryTest extends FunSuite {

  val entity = Roles("1","Male")
  val repository = RolesRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.id), 2 minutes)
    assert(result.head.id==entity.id)
  }

  test("getEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(roleName = "6 Browning RD")
    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.id), 2 minutes)
    assert(result.head.roleName==updatedEntity.roleName)
  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.id), 2 minutes)
    assert(result.isEmpty)
  }


}

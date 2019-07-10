package services.demographics

import domain.demographics.Roles
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class RolesServicesTest extends FunSuite {

  val entity = Roles("1","ND:Application development")
  val service = RoleService
  test("createEntity"){
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(service.roach.getEntity(entity.id), 2 minutes)
    assert(result.head.id==entity.id)
  }

  test("getEntities") {
    val result = Await.result(service.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(roleName = "ND:Application Development")
    Await.result(service.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.id), 2 minutes)
    assert(result.head.roleName==updatedEntity.roleName)
  }


  test("deleteEntities"){
    Await.result(service.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.id), 2 minutes)
    assert(result.isEmpty)

  }

}

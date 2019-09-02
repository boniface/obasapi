package services.demographics

import domain.demographics.Gender
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class GenderServicesTest extends FunSuite {

  val entity = Gender("2", "Male")
  val service = GenderService
  test("createEntity"){
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(service.roach.getEntity(entity.genderId), 2 minutes)
    assert(result.head.genderId==entity.genderId)
  }


  test("getEntities") {
    val result = Await.result(service.roach.getEntities, 2 minutes)
    println(result)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(service.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.genderId), 2 minutes)
    assert(result.isEmpty)

  }
}

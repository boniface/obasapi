package repository.application

import java.time.LocalDateTime

import domain.application.ApplicationStatus
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class ApplicationStatusRepositoryTest extends FunSuite {

  val entity = ApplicationStatus("2","Pending ",LocalDateTime.now)
  val repository = ApplicationStatusRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.id), 2 minutes)
    assert(result.head.id==entity.id)
  }

  test("getEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.id), 2 minutes)
    assert(result.isEmpty)

  }
  
}

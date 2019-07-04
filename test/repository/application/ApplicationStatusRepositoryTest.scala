package repository.application

import java.time.LocalDateTime

import domain.application.ApplicationStatus
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class ApplicationStatusRepositoryTest extends FunSuite {

  val entity = ApplicationStatus("1","Pending ",LocalDateTime.of(2000,5,6,1500,15,5))
  val repository = ApplicationStatusRepository
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.applicationStatusId), 2 minutes)
    assert(result.head.applicationStatusId==entity.applicationStatusId)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(description = "Processed ")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.applicationStatusId), 2 minutes)
    assert(result.head.description==updatedEntity.description)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.applicationStatusId), 2 minutes)
    assert(result.isEmpty)

  }
  
}

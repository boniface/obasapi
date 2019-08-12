package repository.users

import domain.users.UserCommunication
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._


class UserCommunicationRepositoryTest extends FunSuite{
  val entity = UserCommunication("1","test")
  val repository = UserCommunicationRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.communicationId), 2 minutes)
    assert(result.head.communicationId==entity.communicationId)
  }

  test("getEntities") {
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.communicationId), 2 minutes)
    assert(result.isEmpty)

  }
}
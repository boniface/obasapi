package repository.users

import domain.users.UserResults
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._


class UserResultsRepositoryTest extends FunSuite{
  val entity = UserResults("6","failed")
  val repository = UserResultsRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

//  test("readEntity"){
//    val result = Await.result(repository.roach.getEntity(entity.userResultsId), 2 minutes)
//    assert(result.head.userResultsId==entity.userResultsId)
//  }
//
//  test("getEntities") {
//    val result = Await.result(repository.roach.getEntities, 2 minutes)
//    assert(result.nonEmpty)
//  }
//
  test("updateEntity") {
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }
//
//
//  test("deleteEntities"){
//    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
//    val result = Await.result(repository.roach.getEntity(entity.userResultsId), 2 minutes)
//    assert(result.isEmpty)
//
//  }
}
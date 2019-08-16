package repository.users

import domain.users.UserDemographics
import org.scalatest.FunSuite


import scala.concurrent.Await
import scala.concurrent.duration._


class UserDemographicsRepositoryTest extends FunSuite{
  val entity = UserDemographics("41","genderTest", "raceTest")
  val repository = UserDemographicsRepository
  test("createEntity"){
    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(repository.roach.getEntity(entity.userDemographicsId), 2 minutes)
    assert(result.head.userDemographicsId==entity.userDemographicsId)
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
    val result = Await.result(repository.roach.getEntity(entity.userDemographicsId), 2 minutes)
    assert(result.isEmpty)

  }
}
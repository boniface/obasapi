package repository.login

import java.time.LocalDateTime

import domain.login.UserLoginEvents
import org.scalatest.FunSuite

import scala.concurrent.duration._

import scala.concurrent.Await
import scala.language.postfixOps

class UserLoginEventsRepositoryImplTest extends FunSuite {

  val entity= UserLoginEvents("aj.weise@gmail.com","34G",LocalDateTime.now,"String")
  val repository = UserLoginEventsRepository

  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

    test("readEntity"){
      val result = Await.result(repository.apply.getEntity(entity.id), 2 minutes)
      assert(result.head.id==entity.id)
    }

  test("getEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    println(result)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


    test("deleteEntities"){
      Await.result(repository.apply.deleteEntity(entity), 2 minutes)
      val result = Await.result(repository.apply.getEntity(entity.id), 2 minutes)
      assert(result.isEmpty)
    }

}

package repository.mail

import domain.mail.EmailMessage
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class EmailMessageRepositoryTest extends FunSuite{
  val entity = EmailMessage("wieseaj@gmail.com", "PassWord reset", "pls change it")
  val roachRepository = EmailMessageRepository.roach

  test("createEntity") {
    val result = Await.result(roachRepository.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity") {
    val result = Await.result(roachRepository.getEntity(entity.email), 2 minutes)
    assert(result.head.email == entity.email)
  }


  test("getEntities") {
    val result = Await.result(roachRepository.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }
  test("updateEntity") {
    val result = Await.result(roachRepository.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }
  test("deleteEntities") {
    Await.result(roachRepository.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachRepository.getEntity(entity.email), 2 minutes)
    assert(result.isEmpty)

  }

}

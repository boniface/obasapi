package repository.mail

import java.time.LocalDateTime

import domain.mail.MailConfig
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class MailConfigRepositoryTest extends FunSuite {
  val entity = MailConfig("1", "1", "11", "eeee", "host", "908", "active", LocalDateTime.now)
  val roachRepository = MailConfigRepository.roach

  test("createEntity") {
    val result = Await.result(roachRepository.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

//  test("readEntity") {
//    val result = Await.result(roachRepository.getEntity(entity.id), 2 minutes)
//    assert(result.head.id == entity.id)
//  }
//
//
//  test("getEntities") {
//    val result = Await.result(roachRepository.getEntities, 2 minutes)
//    assert(result.nonEmpty)
//  }
//  test("updateEntity") {
//    val result = Await.result(roachRepository.saveEntity(entity), 2 minutes)
//    assert(result.isEmpty)
//
//  }
//    test("deleteEntities") {
//      Await.result(roachRepository.deleteEntity(entity), 2 minutes)
//      val result = Await.result(roachRepository.getEntity(entity.id), 2 minutes)
//      assert(result.isEmpty)
//
//    }


}

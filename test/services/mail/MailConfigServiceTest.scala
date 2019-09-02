package services.mail

import java.time.LocalDateTime

import domain.mail.MailConfig
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class MailConfigServiceTest extends FunSuite {
  val entity = MailConfig("1", "1", "11", "eeee", "host", "908", "active", LocalDateTime.now)
  val service = MailConfigService

  test("createEntity") {
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)
  }

  test("readEntity") {
    val result = Await.result(service.roach.getEntity(entity.id), 2 minutes)
    assert(result.head.id == entity.id)
  }

  test("getEntities") {
    val result = Await.result(service.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities") {
    Await.result(service.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.id), 2 minutes)
    assert(result.isEmpty)

  }


}

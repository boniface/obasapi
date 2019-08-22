package services.mail.impl

import domain.mail.MailApi
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import services.mail.MailApiService
import util.APPKeys

import scala.concurrent.Await
import scala.concurrent.duration._

class MailApiServiceImplTest extends FunSuite with BeforeAndAfterEach {

  val entity = MailApi("SENDGRID", "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", "info@obas.com")
  val service = MailApiService

  test("testCreateTable") {

  }

  test("testGetEntity") {
    println(APPKeys.SENDGRID_ID)
    val result = Await.result(service.roach.getEntity(APPKeys.SENDGRID_ID), 2.minutes)
    println(result.get)
    assert(result.nonEmpty)
  }

  test("testSaveEntity") {
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)
  }

  test("testDeleteEntity") {

  }

  test("testGetEntities") {

  }

}

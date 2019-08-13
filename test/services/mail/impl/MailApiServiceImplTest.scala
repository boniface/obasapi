package services.mail.impl

import domain.mail.MailApi
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import services.mail.MailApiService

import scala.concurrent.Await
import scala.concurrent.duration._

class MailApiServiceImplTest extends FunSuite with BeforeAndAfterEach {

  val entity = MailApi("SENDGRID", "H9STO869Q1uyDIexSvy_KQ", "SENDGRID_PROVIDER")
  val service = MailApiService

  test("testCreateTable") {

  }

  test("testGetEntity") {
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

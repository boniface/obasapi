package services.mail

import domain.mail.{EmailMessage, MailConfig}
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class EmailMessageServiceTest extends  FunSuite{
  val entity = EmailMessage("wieseaj@gmail.com", "PassWord reset", "pls change it")
  val roachService = EmailMessageService.roach

  test("createEntity") {
  val result = Await.result(roachService.saveEntity(entity), 2 minutes)
  assert(result.nonEmpty)

}

  test("readEntity") {
  val result = Await.result(roachService.getEntity(entity.email), 2 minutes)
  assert(result.head.email == entity.email)
}


  test("getEntities") {
  val result = Await.result(roachService.getEntities, 2 minutes)
  assert(result.nonEmpty)
}
  test("updateEntity") {
  val result = Await.result(roachService.saveEntity(entity), 2 minutes)
  assert(result.isEmpty)

}
  test("deleteEntities") {
  Await.result(roachService.deleteEntity(entity), 2 minutes)
  val result = Await.result(roachService.getEntity(entity.email), 2 minutes)
  assert(result.isEmpty)

}

}

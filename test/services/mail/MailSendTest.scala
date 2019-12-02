package services.mail

import domain.mail.EmailMessage
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class MailSendTest extends FunSuite{
  test("sendMail"){
    val service = MailService
    val message = EmailMessage("Final Test ","boniface@kabaso.com","THis is to Test If you get your<b> Messaged </b>")

    val result = Await.result(service.sendGrid.sendMail(message), 2 minutes)
    assert(result.statusCode>0)

  }

}

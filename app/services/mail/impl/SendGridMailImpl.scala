package services.mail.impl

import com.sendgrid._
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.{Content, Email}
import domain.mail.{EmailMessage, MailApi, MessageResponse}
import services.mail.{MailApiService, MailService}
import util.APPKeys

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class SendGridMailImpl extends MailService{

  def sendMail(message:EmailMessage): Future[MessageResponse] = {

    lazy val content = new Content("text/html", message.content)
    lazy val to = new Email(message.email)

    for {
      mailKey: Option[MailApi] <- MailApiService.roach.getEntity(APPKeys.SENDGRID_ID)
    } yield {
      lazy val from = new Email(getKey(mailKey).sender)
      val mail: Mail = new Mail(from, message.subject, to, content)
      val sg = new SendGrid(getKey(mailKey).key)
      val result = postMessage(sg, mail)
      result
    }
  }

  private def postMessage(sg: SendGrid, mail: Mail): MessageResponse = {
    val request = new Request
    request.setMethod(Method.POST)
    request.setEndpoint("mail/send")
    request.setBody(mail.build)
    val response = sg.api(request)
    response.getStatusCode
    MessageResponse(response.getStatusCode,response.getHeaders.asScala.toMap,response.getBody)
  }

  private def getKey(mailKey: Option[MailApi]): MailApi = mailKey match {
    case Some(key) => key
    case None => MailApi.apply()
  }
}

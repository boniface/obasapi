package services.mail.Impl

import com.sendgrid._
import domain.mail.{EmailMessage, MailApi, MessageResponse}
import services.mail.{MailApiService, MailService}

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class SendGridMailImpl extends MailService{

  def sendMail(message:EmailMessage): Future[MessageResponse] = {

    for {
      mailKey: Option[MailApi] <- MailApiService.apply.getEntity(HashcodeKeys.SEDNGRID)
    } yield {
      val mail: Mail = new Mail(new Email(getKey(mailKey).sender),
        message.subject, new Email(message.email),
        new Content("text/html", message.content))
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

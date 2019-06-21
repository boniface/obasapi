package services.mail

import domain.mail.{EmailMessage, MessageResponse}
import services.mail.Impl.SendGridMailImpl

import scala.concurrent.Future

trait MailService {

  def sendMail(message:EmailMessage): Future[MessageResponse]

}

object MailService{
  def sendGrid: MailService = new SendGridMailImpl()
}

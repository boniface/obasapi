package services.mail

import domain.mail.EmailMessage
import services.CrudService
import services.mail.impl.EmailMessageServiceImpl

trait EmailMessageService extends CrudService[EmailMessage]{

}

object EmailMessageService{
  def roach: EmailMessageService = new EmailMessageServiceImpl
}

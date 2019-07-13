package services.mail

import domain.mail.MailApi
import services.CrudService
import services.mail.impl.MailApiServiceImpl

trait MailApiService extends CrudService[MailApi]{

}

object MailApiService{
  def roach: MailApiService = new MailApiServiceImpl
}

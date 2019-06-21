package services.mail

import domain.mail.MailApi
import services.CrudService
import services.mail.Impl.MailApiServiceImpl

trait MailApiService extends CrudService[MailApi]{

}

object MailApiService{
  def apply: MailApiService = new MailApiServiceImpl()
}

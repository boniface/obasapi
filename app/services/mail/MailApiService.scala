package services.mail

import domain.mail.MailApi
import services.CrudService
import services.mail.impl.cockcroachdb

trait MailApiService extends CrudService[MailApi]{

}

object MailApiService{
  def roach: MailApiService = new cockcroachdb.MailApiServiceImpl
}

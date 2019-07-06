package services.mail

import domain.mail.SmtpConfig
import services.CrudService
import services.mail.impl.cockcroachdb

trait SmtpConfigService extends CrudService[SmtpConfig]{

}

object SmtpConfigService {
  def roach: SmtpConfigService = new cockcroachdb.SmtpConfigServiceImpl()
}

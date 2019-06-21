package services.mail

import domain.mail.SmtpConfig
import services.CrudService
import services.mail.Impl.SmtpConfigServiceImpl

trait SmtpConfigService extends CrudService[SmtpConfig]{

}

object SmtpConfigService {
  def apply: SmtpConfigService = new SmtpConfigServiceImpl()
}

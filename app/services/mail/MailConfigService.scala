package services.mail

import domain.mail.MailConfig
import services.CrudService
import services.mail.impl.MailConfigServiceImpl

import scala.concurrent.Future

trait MailConfigService extends CrudService[MailConfig] {
  def getSiteMailConfigurations(siteId: String): Future[Seq[MailConfig]]

}

object MailConfigService {
  def roach: MailConfigService = new MailConfigServiceImpl()
}


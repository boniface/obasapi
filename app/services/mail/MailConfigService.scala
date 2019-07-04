package services.mail

import domain.mail.MailConfig
import services.CrudService
import services.mail.impl.cockcroachdb

import scala.concurrent.Future

trait MailConfigService extends CrudService[MailConfig] {
  def getSiteMailConfigurations(siteId: String): Future[Seq[MailConfig]]

}

object MailConfigService {
  def roach: MailConfigService = new cockcroachdb.MailConfigServiceImpl()
}


package repository.mail

import domain.mail.MailConfig
import repository.Repository
import repository.mail.Impl.cassandra.MailConfigRepositoryImpl

import scala.concurrent.Future

trait MailConfigRepository extends Repository[MailConfig]{
  def getSiteMailConfigurations(siteId:String): Future[Seq[MailConfig]]
}

object MailConfigRepository{
  def apply: MailConfigRepository = new MailConfigRepositoryImpl()
}

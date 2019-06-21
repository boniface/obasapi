package repository.mail

import domain.mail.MailApi
import repository.Repository
import repository.mail.Impl.cassandra.MailApiRepositoryImpl

trait MailApiRepository extends Repository[MailApi] {

}

object MailApiRepository{
  def apply: MailApiRepository = new MailApiRepositoryImpl()
}

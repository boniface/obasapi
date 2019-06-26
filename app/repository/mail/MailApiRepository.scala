package repository.mail

import domain.mail.MailApi
import repository.Repository
import repository.mail.Impl.cockcroachdb

trait MailApiRepository extends Repository[MailApi] {

}

object MailApiRepository{
  def apply: MailApiRepository = new cockcroachdb.MailApiRepositoryImpl
}

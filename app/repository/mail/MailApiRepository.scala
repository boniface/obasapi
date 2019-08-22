package repository.mail

import domain.mail.MailApi
import repository.Repository
import repository.mail.impl.cockcroachdb.MailApiRepositoryImpl

trait MailApiRepository extends Repository[MailApi] {

}

object MailApiRepository{
  def roach: MailApiRepository = new MailApiRepositoryImpl
}

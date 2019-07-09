package repository.mail

import domain.mail.MailApi
import repository.Repository
import repository.mail.impl.cockcroachdb
import repository.mail.impl.cassandra

trait MailApiRepository extends Repository[MailApi] {

}

object MailApiRepository{
  def roach: MailApiRepository = new cockcroachdb.MailApiRepositoryImpl
  def cass: MailApiRepository = new cassandra.MailApiRepositoryImpl
}

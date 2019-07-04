package repository.mail

import domain.mail.MailApi
import repository.Repository
import repository.mail.Impl.cockcroachdb
import repository.mail.Impl.cassandra

trait MailApiRepository extends Repository[MailApi] {

}

object MailApiRepository{
  def roach: MailApiRepository = new cockcroachdb.MailApiRepositoryImpl
  def cass: MailApiRepository = new cassandra.MailApiRepositoryImpl
}

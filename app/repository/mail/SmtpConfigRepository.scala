package repository.mail

import domain.mail.SmtpConfig
import repository.Repository
import repository.mail.impl.{cassandra, cockcroachdb}

trait SmtpConfigRepository extends Repository[SmtpConfig]{

}

object SmtpConfigRepository {
  def cass: SmtpConfigRepository = new cassandra.SmtpConfigRepositoryImpl()
  def roach: SmtpConfigRepository = new cockcroachdb.SmtpConfigRepositoryImpl()
}

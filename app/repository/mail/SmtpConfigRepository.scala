package repository.mail

import domain.mail.SmtpConfig
import repository.Repository
import repository.mail.Impl.cassandra.SmtpConfigRepositoryImpl

trait SmtpConfigRepository extends Repository[SmtpConfig]{

}

object SmtpConfigRepository {
  def apply: SmtpConfigRepository = new SmtpConfigRepositoryImpl()
}

package repository.mail

import domain.mail.EmailMessage
import repository.Repository
import repository.mail.impl.cockcroachdb.EmailMessageRepositoryImpl

trait EmailMessageRepository extends Repository[EmailMessage]{

}
object EmailMessageRepository{
  def roach: EmailMessageRepository = new EmailMessageRepositoryImpl
}

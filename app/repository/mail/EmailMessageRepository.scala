package repository.mail

import domain.mail.EmailMessage
import repository.Repository
import repository.mail.impl.cockcroachdb

trait EmailMessageRepository extends Repository[EmailMessage]{

}
object EmailMessageRepository{
  def roach: EmailMessageRepository = new cockcroachdb.EmailMessageRepositoryImpl
}

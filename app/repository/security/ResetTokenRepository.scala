package repository.security

import domain.security.ResetToken
import repository.Repository


trait  ResetTokenRepository extends Repository[ResetToken]{

}

object ResetTokenRepository{
  def apply: ResetTokenRepository = new ResetTokenRepositoryImpl()
}


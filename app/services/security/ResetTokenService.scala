package services.security

import domain.security.ResetToken
import services.CrudService
import services.security.impl.ResetTokenServiceImpl

trait ResetTokenService extends CrudService[ResetToken] {

}
object ResetTokenService{
  def apply: ResetTokenService = new ResetTokenServiceImpl()
}


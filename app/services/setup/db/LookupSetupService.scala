package services.setup.db

import services.setup.db.impl.cockroachdb.LookupSetupServiceImpl

import scala.concurrent.Future

trait LookupSetupService {
  def loadLookupData: Future[Boolean]
}

object LookupSetupService {
  def apply: LookupSetupService = new LookupSetupServiceImpl()
}
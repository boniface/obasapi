package services.setup.db

import services.setup.db.impl.cockroachdb.DBSetupServiceImpl

import scala.concurrent.Future

/**
  * Set up service for cockroach DB
  */
trait DBSetupService {
  def createTables: Future[Boolean]
}

object DBSetupService {
  def roachImpl: DBSetupService = new DBSetupServiceImpl()
}

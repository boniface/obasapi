package services.setup.db.impl.cockroachdb

import services.mail.MailApiService
import services.setup.db.DBSetupService

import scala.concurrent.Future

class DBSetupServiceImpl extends DBSetupService{
  override def createTables: Future[Boolean] = {
    MailApiService.roach.createTable
  }
}

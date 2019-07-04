package services.setup.db.impl.cockroachdb

import services.mail.MailApiService
import services.setup.db.DBSetupService
import util.connections.PgDBConnection

import scala.concurrent.Future

class DBSetupServiceImpl extends DBSetupService{
  override def createTables: Future[Boolean] = {
    println("creating mail api table...")
    println(78)
    println(9, PgDBConnection.cokdb)
    MailApiService.roach.createTable
  }
}

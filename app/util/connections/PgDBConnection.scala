package util.connections

import slick.jdbc.PostgresProfile
import util.connections.DataConnection.config

object PgDBConnection {
  def cokdb = config.getString("cockroachdb")
  val driver = PostgresProfile
  import driver.api._
  val db: Database = null//Database.forConfig(cokdb)

}

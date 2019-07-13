package util.connections

import com.typesafe.config.{Config, ConfigFactory}
import slick.jdbc.PostgresProfile

object PgDBConnection {
  val config: Config = ConfigFactory.load()
  val driver = PostgresProfile
  import driver.api._
  val db = Database.forConfig("cockroachdb")
}

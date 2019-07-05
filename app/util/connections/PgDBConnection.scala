package util.connections

import com.typesafe.config.{Config, ConfigFactory}
import slick.basic.DatabaseConfig
import slick.jdbc.PostgresProfile
import util.connections.DataConnection.config

//object PgDBConnection {
//  def cokdb = config.getString("cockroachdb")
//  val driver = PostgresProfile
//  import driver.api._
//  val db: Database = Database.forConfig(cokdb)
//
//}

object PgDBConnection {
  val config: Config = ConfigFactory.load()
  val driver = PostgresProfile
  val dbConfig: DatabaseConfig[PostgresProfile] = DatabaseConfig.forConfig("cockroachdb.default")
  val db = dbConfig.db

}

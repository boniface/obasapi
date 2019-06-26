package util.connections

import slick.jdbc.PostgresProfile

object PgDBConnection {
  val driver = PostgresProfile
  import driver.api._
  val db: Database = Database.forConfig("postgres")

}

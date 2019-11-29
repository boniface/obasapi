package repository.users.impl.cockroachdb.tables

import domain.users.UserApplicationStatus
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver
import java.time.LocalDateTime


import scala.concurrent.Future

class UserApplicationStatusTable(tag: Tag) extends Table[UserApplicationStatus](tag, "user_application_status"){{

  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def genderId: Rep[String] = column[String]("gender_id")

  def raceId: Rep[String] = column[String]("race_id")

  def titleId: Rep[String] = column[String]("title_id")

  def * : ProvenShape[UserApplicationStatus] = (userId, genderId, raceId,titleId) <> ((UserApplicationStatus.apply _).tupled, UserApplicationStatus.unapply)
}

  object UserDemographicsTable extends TableQuery(new UserDemographicsTable(_)) {
    def db: driver.api.Database = PgDBConnection.db

    def getEntity(userId: String): Future[Option[UserApplicationStatus]] = {
      db.run(this.filter(_.userId === userId).result).map(_.headOption)
    }

    def saveEntity(userDemographics: UserApplicationStatus): Future[Option[UserApplicationStatus]] = {
      db.run(
        (this returning this).insertOrUpdate(userDemographics)
      )
    }

    def getEntities: Future[Seq[UserApplicationStatus]] = {
      db.run(UserDemographicsTable.result)
    }

    def deleteEntity(userId: String): Future[Int] = {
      db.run(this.filter(_.userId === userId).delete)
    }

    def createTable = {
      db.run(
        UserDemographicsTable.schema.createIfNotExists
      ).isCompleted
    }


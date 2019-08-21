package repository.users.impl.cockroachdb.tables

import domain.users.UserDemographics
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserDemographicsTable(tag: Tag) extends Table[UserDemographics](tag, "user_demographics") {
  def userDemographicsId: Rep[String] = column[String]("user_demographics_id", O.PrimaryKey)

  def genderId: Rep[String] = column[String]("gender_id")

  def raceId: Rep[String] = column[String]("race_id")

  def * : ProvenShape[UserDemographics] = (userDemographicsId, genderId, raceId) <> ((UserDemographics.apply _).tupled, UserDemographics.unapply)
}

object UserDemographicsTable extends TableQuery(new UserDemographicsTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userDemographicsId: String): Future[Option[UserDemographics]] = {
    db.run(this.filter(_.userDemographicsId === userDemographicsId).result).map(_.headOption)
  }

  def saveEntity(userDemographics: UserDemographics): Future[Option[UserDemographics]] = {
    db.run(
      (this returning this).insertOrUpdate(userDemographics)
    )
  }

  def getEntities: Future[Seq[UserDemographics]] = {
    db.run(UserDemographicsTable.result)
  }

  def deleteEntity(userDemographicsId: String): Future[Int] = {
    db.run(this.filter(_.userDemographicsId === userDemographicsId).delete)
  }

  def createTable = {
    db.run(
      UserDemographicsTable.schema.createIfNotExists
    ).isCompleted
  }

}
package repository.users.impl.cockroachdb.tables

import domain.users.UserDemographics
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserDemographicsTable(tag: Tag) extends Table[UserDemographics](tag, "user_demographics") {
  def userId: Rep[String] = column[String]("user_demographics_id", O.PrimaryKey)

  def genderId: Rep[String] = column[String]("gender_id")

  def raceId: Rep[String] = column[String]("race_id")
  
  def titleId: Rep[String] = column[String]("title_id")
  


  def * : ProvenShape[UserDemographics] = (userId, genderId, raceId,titleId) <> ((UserDemographics.apply _).tupled, UserDemographics.unapply)
}

object UserDemographicsTable extends TableQuery(new UserDemographicsTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String): Future[Option[UserDemographics]] = {
    db.run(this.filter(_.userId === userId).result).map(_.headOption)
  }

  def saveEntity(userDemographics: UserDemographics): Future[Option[UserDemographics]] = {
    db.run(
      (this returning this).insertOrUpdate(userDemographics)
    )
  }

  def getEntities: Future[Seq[UserDemographics]] = {
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

}
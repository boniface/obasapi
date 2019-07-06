package repository.users.impl.cockroachdb.tables

import domain.users.UserInstitution
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserInstitutionTable(tag: Tag) extends Table[UserInstitution](tag, "USERINSTITUTION") {
  def userInstitutionId: Rep[String] = column[String]("USER_INSTITUTION_ID", O.PrimaryKey)

  def name: Rep[String] = column[String]("NAME")

  def * : ProvenShape[UserInstitution] = (userInstitutionId, name) <> ((UserInstitution.apply _).tupled, UserInstitution.unapply)
}

object UserInstitutionTable extends TableQuery(new UserInstitutionTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userInstitutionId: String): Future[Option[UserInstitution]] = {
    db.run(this.filter(_.userInstitutionId === userInstitutionId).result).map(_.headOption)
  }

  def saveEntity(userInstitution: UserInstitution): Future[UserInstitution] = {
    db.run(this returning this.map(_.userInstitutionId) into ((acc, userInstitutionId) => acc.copy(userInstitutionId = userInstitutionId)) += userInstitution)
  }

  def getEntities: Future[Seq[UserInstitution]] = {
    db.run(UserInstitutionTable.result)
  }

  def deleteEntity(userInstitutionId: String): Future[Int] = {
    db.run(this.filter(_.userInstitutionId === userInstitutionId).delete)
  }

  def createTable = {
    db.run(
      UserInstitutionTable.schema.createIfNotExists
    ).isCompleted
  }

}
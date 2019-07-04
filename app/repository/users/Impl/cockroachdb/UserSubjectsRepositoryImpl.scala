package repository.users.Impl.cockroachdb

import domain.users.UserSubjects
import repository.users.Impl.cockroachdb.tables.UserSubjectsTable
import repository.users.UserSubjectsRepository

import scala.concurrent.Future

class UserSubjectsRepositoryImpl  extends UserSubjectsRepository{

  override def saveEntity(entity: UserSubjects): Future[Boolean] = {
    Future.successful(UserSubjectsTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserSubjects]] = {
    UserSubjectsTable.getEntities
  }

  override def getEntity(UserSubjectsId: String): Future[Option[UserSubjects]] = {
    UserSubjectsTable.getEntity(UserSubjectsId)
  }

  override def deleteEntity(entity: UserSubjects): Future[Boolean] = {
    Future.successful(UserSubjectsTable.deleteEntity(entity.userSubjectId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserSubjectsTable.createTable)
  }
}


package repository.users.impl.cockroachdb

import domain.users.UserMatricSubject
import repository.users.UserMatricSubjectRepository
import repository.users.impl.cockroachdb.tables.{UserMatricSubjectTable, UserMatricSubjectTableCreate}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserMatricSubjectRepositoryImpl extends UserMatricSubjectRepository {
  override def getEntity(userId: String, subjectId: String): Future[Option[UserMatricSubject]] =
    UserMatricSubjectTable.getEntity(userId, subjectId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserMatricSubject]] =
    UserMatricSubjectTable.getEntitiesForUser(userId)

  override def saveEntity(entity: UserMatricSubject): Future[Option[UserMatricSubject]] =
    UserMatricSubjectTable.saveEntity(entity)

  override def getEntities: Future[Seq[UserMatricSubject]] =
    UserMatricSubjectTable.getEntities

  override def getEntity(id: String): Future[Option[UserMatricSubject]] = ???

  override def deleteEntity(entity: UserMatricSubject): Future[Boolean] =
    UserMatricSubjectTable.deleteEntity(entity.userId, entity.subjectId).map(value => value.isValidInt)

  override def createTable: Future[Boolean] =
    Future.successful(UserMatricSubjectTableCreate.createTable)
}

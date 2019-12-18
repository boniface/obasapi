package repository.users.impl.cockroachdb

import domain.users.UserTertiarySubject
import repository.users.UserTertiarySubjectRepository
import repository.users.impl.cockroachdb.tables.{UserTertiarySubjectTable, UserTertiarySubjectTableCreate}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserTertiarySubjectRepositoryImpl extends UserTertiarySubjectRepository {
  override def getEntity(userId: String, applicationId: String, subjectId: String): Future[Option[UserTertiarySubject]] =
    UserTertiarySubjectTable.getEntity(userId, applicationId, subjectId)

  override def getEntitiesForApplication(userId: String, applicationId: String): Future[Seq[UserTertiarySubject]] =
    UserTertiarySubjectTable.getEntitiesForApplication(userId, applicationId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserTertiarySubject]] =
    UserTertiarySubjectTable.getEntitiesForUser(userId)

  override def deleteEntitiesForApplication(userId: String, applicationId: String): Future[Boolean] =
    UserTertiarySubjectTable.deleteEntitiesForApplication(userId, applicationId).map(value => value.isValidInt)

  override def saveEntity(entity: UserTertiarySubject): Future[Option[UserTertiarySubject]] =
    UserTertiarySubjectTable.saveEntity(entity)

  override def getEntities: Future[Seq[UserTertiarySubject]] =
    UserTertiarySubjectTable.getEntities

  override def getEntity(id: String): Future[Option[UserTertiarySubject]] = ???

  override def deleteEntity(entity: UserTertiarySubject): Future[Boolean] =
    UserTertiarySubjectTable.deleteEntity(entity.userId, entity.applicationId, entity.subjectId)
    .map(value => value.isValidInt)

  override def createTable: Future[Boolean] =
    Future.successful(UserTertiarySubjectTableCreate.createTable)
}

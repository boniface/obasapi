package repository.users.impl.cockroachdb

import domain.users.UserSubject
import repository.users.impl.cockroachdb.tables.{UserSubjectTable, UserSubjectTableCreate}
import repository.users.UserSubjectRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserSubjectRepositoryImpl  extends UserSubjectRepository{
  override def getEntity(userId: String, institutionId: String, subjectId: String): Future[Option[UserSubject]] =
    UserSubjectTable.getEntity(userId, institutionId, subjectId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserSubject]] =
    UserSubjectTable.getEntitiesForUser(userId)

  override def getEntitiesForUserPerInstitution(userId: String, institutionId: String): Future[Seq[UserSubject]] =
    UserSubjectTable.getEntitiesForUserPerInstitution(userId, institutionId)

  override def saveEntity(entity: UserSubject): Future[Option[UserSubject]] =
    UserSubjectTable.saveEntity(entity)

  override def getEntities: Future[Seq[UserSubject]] = UserSubjectTable.getEntities

  override def getEntity(id: String): Future[Option[UserSubject]] = ???

  override def deleteEntity(entity: UserSubject): Future[Boolean] =
    UserSubjectTable.deleteEntity(entity.userId, entity.institutionId, entity.subjectId).map(value => value.isValidInt)

  override def createTable: Future[Boolean] =
    Future.successful(UserSubjectTableCreate.createTable)
}

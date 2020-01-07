package services.users.Impl

import domain.users.UserTertiarySubject
import repository.users.UserTertiarySubjectRepository
import services.users.UserTertiarySubjectService

import scala.concurrent.Future

class UserTertiarySubjectServiceImpl extends UserTertiarySubjectService {
  override def getEntity(userId: String, applicationId: String, subjectId: String): Future[Option[UserTertiarySubject]] =
    UserTertiarySubjectRepository.apply.getEntity(userId, applicationId, subjectId)

  override def getEntitiesForApplication(userId: String, applicationId: String): Future[Seq[UserTertiarySubject]] =
    UserTertiarySubjectRepository.apply.getEntitiesForApplication(userId, applicationId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserTertiarySubject]] =
    UserTertiarySubjectRepository.apply.getEntitiesForUser(userId)

  override def deleteEntitiesForApplication(userId: String, applicationId: String): Future[Boolean] =
    UserTertiarySubjectRepository.apply.deleteEntitiesForApplication(userId, applicationId)

  override def saveEntity(entity: UserTertiarySubject): Future[Option[UserTertiarySubject]] =
    UserTertiarySubjectRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[UserTertiarySubject]] =
    UserTertiarySubjectRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[UserTertiarySubject]] = ???

  override def deleteEntity(entity: UserTertiarySubject): Future[Boolean] =
    UserTertiarySubjectRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = UserTertiarySubjectRepository.apply.createTable
}

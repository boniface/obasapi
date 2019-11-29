package services.users.Impl

import domain.users.UserSubject
import repository.users.UserSubjectRepository
import services.users.UserSubjectService

import scala.concurrent.Future

class UserSubjectServiceImpl extends UserSubjectService {
  override def getEntity(userId: String, institutionId: String, subjectId: String): Future[Option[UserSubject]] =
    UserSubjectRepository.roach.getEntity(userId, institutionId, subjectId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserSubject]] =
    UserSubjectRepository.roach.getEntitiesForUser(userId)

  override def getEntitiesForUserPerInstitution(userId: String, institutionId: String): Future[Seq[UserSubject]] =
    UserSubjectRepository.roach.getEntitiesForUserPerInstitution(userId, institutionId)

  override def saveEntity(entity: UserSubject): Future[Option[UserSubject]] =
    UserSubjectRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserSubject]] =
    UserSubjectRepository.roach.getEntities

  override def getEntity(id: String): Future[Option[UserSubject]] = ???

  override def deleteEntity(entity: UserSubject): Future[Boolean] = UserSubjectRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] = UserSubjectRepository.roach.createTable
}

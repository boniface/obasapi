package services.users.Impl

import domain.users.UserMatricSubject
import repository.users.UserMatricSubjectRepository
import services.users.UserMatricSubjectService

import scala.concurrent.Future

class UserMatricSubjectServiceImpl extends UserMatricSubjectService {
  override def getEntity(userId: String, subjectId: String): Future[Option[UserMatricSubject]] =
    UserMatricSubjectRepository.apply.getEntity(userId, subjectId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserMatricSubject]] =
    UserMatricSubjectRepository.apply.getEntitiesForUser(userId)

  override def saveEntity(entity: UserMatricSubject): Future[Option[UserMatricSubject]] =
    UserMatricSubjectRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[UserMatricSubject]] =
    UserMatricSubjectRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[UserMatricSubject]] = ???

  override def deleteEntity(entity: UserMatricSubject): Future[Boolean] =
    UserMatricSubjectRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = UserMatricSubjectRepository.apply.createTable
}

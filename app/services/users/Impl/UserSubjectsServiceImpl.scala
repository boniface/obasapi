package services.users.Impl

import domain.users.UserSubjects
import repository.users.UserSubjectsRepository
import services.users.UserSubjectsService

import scala.concurrent.Future

class UserSubjectsServiceImpl extends UserSubjectsService {

  override def saveEntity(entity: UserSubjects): Future[Boolean] =
    UserSubjectsRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserSubjects]] =
    UserSubjectsRepository.roach.getEntities

  override def getEntity(userSubjectId: String): Future[Option[UserSubjects]] =
    UserSubjectsRepository.roach.getEntity(userSubjectId)

  override def deleteEntity(entity: UserSubjects): Future[Boolean] =
    UserSubjectsRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserSubjectsRepository.roach.createTable

}

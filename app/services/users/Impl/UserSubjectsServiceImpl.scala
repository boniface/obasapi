package services.users.Impl

import domain.users.UserSubjects
import services.users.UserSubjectsService

import scala.concurrent.Future

class UserSubjectsServiceImpl extends UserSubjectsService {

  override def saveEntity(entity: UserSubjects): Future[Boolean] = ???

  override def getEntities: Future[Seq[UserSubjects]] = ???

  override def getEntity(userSubjectId: String): Future[Option[UserSubjects]] = ???

  override def deleteEntity(entity: UserSubjects): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???

}

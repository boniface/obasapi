package services.users.Impl

import domain.users.UserInstitution
import services.users.UserInstitutionService

import scala.concurrent.Future

class UserInstitutionServiceImpl extends UserInstitutionService {

  override def saveEntity(entity: UserInstitution): Future[Boolean] = ???

  override def getEntities: Future[Seq[UserInstitution]] = ???

  override def getEntity(id: String): Future[Option[UserInstitution]] = ???

  override def deleteEntity(entity: UserInstitution): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???

}

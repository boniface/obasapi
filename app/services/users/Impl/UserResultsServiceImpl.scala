package services.users.Impl

import domain.users.UserResults
import services.users.UserResultsService

import scala.concurrent.Future

class UserResultsServiceImpl extends UserResultsService{

  override def saveEntity(entity: UserResults): Future[Boolean] = ???

  override def getEntities: Future[Seq[UserResults]] = ???

  override def getEntity(userResultsId: String): Future[Option[UserResults]] = ???

  override def deleteEntity(entity: UserResults): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???

}

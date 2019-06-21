package services.users.Impl

import domain.users.User
import services.users.UserService

import scala.concurrent.Future

class UserServiceImpl extends UserService {

  override def saveEntity(entity: User): Future[Boolean] = ???

  override def getEntities: Future[Seq[User]] = ???

  override def getEntity(id: String): Future[Option[User]] = ???

  override def deleteEntity(entity: User): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???

}

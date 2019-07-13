package services.users.Impl

import domain.login.Register
import domain.users.{User, UserPassword, UserRole}
import repository.users.UserRepository
import services.security.AuthenticationService
import services.users.{UserPasswordService, UserService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserServiceImpl extends UserService {

  override def saveEntity(entity: User): Future[Boolean] =
    UserRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[User]] =
    UserRepository.roach.getEntities

  override def getEntity(id: String): Future[Option[User]] =
    UserRepository.roach.getEntity(id)

  override def deleteEntity(entity: User): Future[Boolean] =
    UserRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] = UserRepository.roach.createTable

  override def getUserByRole(email: String): Future[Seq[User]] = ???

  override def isUserAvailable(email: String): Future[Boolean] = {
    getEntity(email).map(user=> user.isDefined)
  }

  override def registerUser(registration: Register): Future[Boolean] = ???

  override def createUser(user: User, userRole: UserRole): Future[Boolean] = ???

  override def changePassword(credentials: UserPassword): Future[Boolean] = {
  val hashedPassword = credentials.copy(password = AuthenticationService.apply.getHashedPassword(credentials.password))
    UserPasswordService.apply.saveEntity(hashedPassword)
  }
}

package services.login.Impl

import com.typesafe.config.ConfigFactory
import domain.demographics.Roles
import domain.login.{Login, LoginToken, Register}
import domain.users.{User, UserPassword, UserRole}
import play.api.mvc.Request
import services.demographics.RoleService
import services.login.{LoginService, LoginTokenService}
import services.mail.{EmailCreationMessageService, MailService}
import services.security.{AuthenticationService, TokenCreationService}
import services.users.{UserPasswordService, UserRoleService, UserService}
import util.APPKeys

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class LoginServiceImpl extends LoginService {
  def isSecurityEnabled: Boolean = ConfigFactory.load().getBoolean("token-security.enabled")

  override def isUserRegistered(user: Register): Future[Boolean] = {
    UserService.apply.isUserAvailable(user.email)
  }

  override def forgotPassword(register: Register): Future[Boolean] = ???

  //TODO: Write test case
  override def register(register: Register): Future[Boolean] = {
    val tempPass = AuthenticationService.apply.generateRandomPassword() // generated password
    val hashedTempPass = AuthenticationService.apply.getHashedPassword(tempPass) // hash passwrd
    val user = User(register.email)
    val userRole = UserRole(user.email, APPKeys.STUDENTROLE)
    val userPassword = UserPassword(user.email, hashedTempPass)
    val emailMessage = EmailCreationMessageService.apply.createNewAccountMessage(user, tempPass) // get Email Message
    for {
      _ <- isUserRegistered(register) //check if user is available
      _ <- UserService.apply.saveEntity(user) // save the user
      _ <- UserPasswordService.apply.saveEntity(userPassword) //save hashed
      _ <- UserRoleService.roach.saveEntity(userRole) //save the role
      sendEmail <- MailService.sendGrid.sendMail(emailMessage)
    } yield {
      sendEmail.statusCode == 202
    }
  }

  def extractUser(user: Option[User]): User = user.getOrElse(null)

  def extractUserRoleId(userRole: Option[UserRole]): String = if (userRole.isDefined) userRole.get.roleId else null

  def extractRoleName(role: Option[Roles]): String = if (role.isDefined) role.get.roleName else null

  def extractUserPassword(userPassword: Option[UserPassword]) =
    if (userPassword.isDefined) userPassword.get.password else null;

  def authenticateUser(password: String, actualPass: String) : Future[Boolean] = {
    Future {
      AuthenticationService.apply.checkPassword(password, actualPass) // compare
    }
  }

  def saveLoginToken(email: String, token: String): Future[Option[LoginToken]] = {
    val loginToken = LoginToken(email, token)
    for {
      _ <- LoginTokenService.apply.saveEntity(loginToken)
    } yield {
      Some(loginToken)
    }
  }

  //TODO: Write test case
  override def getLoginToken(login: Login): Future[Option[LoginToken]] = {
    val register = Register(login.email)
    for {
      _ <- isUserRegistered(register) //check if user is available
      userPassword <- UserPasswordService.apply.getEntity(login.email) //get hashpassword
      _ <- authenticateUser(login.password, extractUserPassword(userPassword))
      user <- UserService.apply.getEntity(login.email)
      userRole <- UserRoleService.roach.getEntity(login.email) // Get The User Role
      role <- RoleService.roach.getEntity(extractUserRoleId(userRole))
      token <- TokenCreationService.apply.generateLoginToken(extractUser(user), extractRoleName(role)) // Generate the Token
      saveToken <- saveLoginToken(login.email, token) // Save Token in LoginToken
    } yield {
      saveToken
    }
  }

  override def resetPasswordRequest(resetKey: String): Future[Boolean] = ??? // {

//     //request for email
//     //check email saved
//    //compare email entered email
//    // send resent link to email
//    // changed email
//
//  }

  override def checkLoginStatus[A](request: Request[A]): Future[Boolean] = {
    val token = request.headers.get(APPKeys.AUTHORIZATION).getOrElse("")
    val email = LoginTokenService.apply.getUserEmail(token)
    if (isSecurityEnabled) {
      if (LoginTokenService.apply.isTokenValid(token).isRight) {
        for {
          token <- LoginTokenService.apply.getEntity(email)
          // Might need to create a cache if Speed become an Issue
        } yield token.isDefined
      } else Future.successful(false)
    } else Future.successful(true)
  }

  override def logOut(register: Register): Future[Boolean] = {
    val emailToken = LoginToken(register.email, "")

    LoginTokenService.apply.deleteEntity(emailToken)
  }
}




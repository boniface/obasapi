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

  def getRoleId(userRole: Option[UserRole]) = {
    userRole match {
      case Some(value) => value.roleId
      case None => ""
    }
  }

  def getRoleName(role: Option[Roles]) = role match {
    case Some(value) => value.roleName
    case None => ""
  }

  def getRoleFromOption(userRole: Option[UserRole]): Future[String] = {
    if (userRole.isDefined) {
      for {
        role <- RoleService.roach.getEntity(userRole.get.roleId)
      } yield {
        val roleName = getRoleName(role)
        roleName
      }
    } else Future.successful(null)
  }

  def getUser(user: Option[User]): User = user.getOrElse(null)

  //TODO: Still in progress...
  override def getLoginToken(login: Login): Future[Option[LoginToken]] = {
    val register = Register(login.email)
    for {
      _ <- isUserRegistered(register) //check if user is available
      userPassword <- UserPasswordService.apply.getEntity(login.email) //get hashpassword
      u <- UserService.apply.getEntity(login.email)
      userRole <- UserRoleService.roach.getEntity(login.email) // Get The User Role
      roleName <- getRoleFromOption(userRole)
      token <- TokenCreationService.apply.generateLoginToken(getUser(u), roleName)
    } yield {

    }

    Future.successful(null)
    // compare
    // Generate the Token
    // Save Token in LoginToken
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




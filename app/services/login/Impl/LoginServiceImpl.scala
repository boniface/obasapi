package services.login.Impl

import com.typesafe.config.ConfigFactory
import domain.login.{Login, LoginToken, Register}
import domain.security.ResetToken
import domain.users.{User, UserPassword, UserRole}
import play.api.mvc.Request
import services.login.{LoginService, LoginTokenService}
import services.mail.{EmailCreationMessageService, MailService}
import services.security.{ApiKeysService, AuthenticationService, ResetTokenService, TokenCreationService}
import services.users.{UserPasswordService, UserRoleService, UserService}
import util.APPKeys

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class LoginServiceImpl extends LoginService {
  def isSecurityEnabled: Boolean = ConfigFactory.load().getBoolean("token-security.enabled")

  override def isUserRegistered(user: Register): Future[Boolean] = {
    UserService.apply.isUserAvailable(user.email)
  }

  override def forgotPassword(register: Register): Future[Option[ResetToken]] =  {
    val siteUrl = ConfigFactory.load().getString("base.url")
    val user = User(email = register.email)
    val resetKey = ApiKeysService.apply.generateResetToken()
    val resetToken = ResetToken(resetKey, register.email)
    val resetMessage = EmailCreationMessageService.apply.forgetPasswordLinkMessage(user, resetKey, siteUrl)
    for {
      saveToken <- ResetTokenService.apply.saveEntity(resetToken)
      _ <- MailService.sendGrid.sendMail(resetMessage)
    } yield saveToken
  }

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

  private def authenticateUser(password: String, actualPass: String): Future[Boolean] = {
    Future.successful(AuthenticationService.apply
      .checkPassword(password, actualPass) )// compare
  }

  private def saveLoginToken(email: String, token: String): Future[Option[LoginToken]] = {
    val loginToken = LoginToken(email, token)
    for {
      _ <- LoginTokenService.apply.saveEntity(loginToken)
    } yield {
      Some(loginToken)
    }
  }


  //TODO: Write test case
  override def getLoginToken(login: Login): Future[Option[LoginToken]] = {
     for {
      user <- UserService.apply.getEntity(login.email) if user.isDefined
      userPassword <- UserPasswordService.apply.getEntity(login.email) if userPassword.isDefined
      userRole <-UserRoleService.roach.getEntity(login.email) if userRole.isDefined
      checkPasswd <- authenticateUser(login.email, userPassword.get.password) if checkPasswd
      token <- TokenCreationService.apply.generateLoginToken(user.get,userRole.get.roleId)
      loginToken <- saveLoginToken(login.email, token)
    } yield  loginToken
  }

  override def resetPasswordRequest(resetKey: String): Future[Boolean] = {
    for {
      resetToken <- ResetTokenService.apply.getEntity(resetKey) if resetToken.isDefined
      user <- UserService.apply.getEntity(resetToken.get.email)
      send <- resetAccount(user)
    } yield send
  }

  private def resetAccount(user: Option[User]): Future[Boolean] = {
    val generatedPassword = AuthenticationService.apply.generateRandomPassword()
    lazy val newHashedPassword = AuthenticationService.apply.getHashedPassword(generatedPassword)
    lazy val userPassword = UserPassword(user.get.email, newHashedPassword)
    lazy val emailMessage = EmailCreationMessageService.apply.passwordResetMessage(user.get, generatedPassword)
    for {
      _ <- UserPasswordService.apply.saveEntity(userPassword)
      sent <- MailService.sendGrid.sendMail(emailMessage)
    } yield sent.statusCode == 202
  }

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

  override def checkFileSize(size: Long): Future[Boolean] = {
    Future.successful(size < 10000000)
  }




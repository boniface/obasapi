package services.mail

import domain.mail.EmailMessage
import domain.users.User
import services.mail.impl.EmailCreationMessageServiceImpl

trait EmailCreationMessageService {

  def accountCreatedMessage(user:User,generatedPassword:String ): EmailMessage

  def customUserMessage(subject:String, user: User, message:String):EmailMessage

  def passwordResetMessage(user:User, password:String): EmailMessage

  def createNewAccountMessage(user: User, password: String):EmailMessage

  def resetPasswordMessage(user: User, password: String): EmailMessage

  def forgetPasswordLinkMessage(user: User, resetKey: String, siteUrl:String): EmailMessage
}

object EmailCreationMessageService{

  def apply: EmailCreationMessageService = new EmailCreationMessageServiceImpl()
}

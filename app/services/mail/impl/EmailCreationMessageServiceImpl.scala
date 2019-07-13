package services.mail.impl

import domain.mail.EmailMessage
import domain.users.User
import services.mail.EmailCreationMessageService

class EmailCreationMessageServiceImpl extends EmailCreationMessageService {

  override def accountCreatedMessage(user: User, generatedPassword: String): EmailMessage = {
    val subject = " New Account Created For You"
    val message = "Your Login Details are Username: " + user.email + " And the Password: " + generatedPassword + "" +
      "</p> You can access the Site  Provided to you By the Provider. " +
      "<b>PLEASE REMEMBER TO CHANGE YOUR PASSWORD</b><p/>" +
      "We are Sure your Superiors have told you that Great Powers Come with Great Responsibility"
    EmailMessage(subject, user.email, message)
  }

  override def customUserMessage(subject: String, user: User, message: String): EmailMessage = {

    val msg = "<html>" +
      "<body>" +
      "<h2><u>The Message Content</u></h2>" +
      "Dear " + user.firstName + " " + user.lastName + ",<p/>" + message + "</body></html>"
    EmailMessage(subject, user.email, msg)
  }

  override def passwordResetMessage(user: User, generatedPassword: String): EmailMessage = {

    val subject = "Your Reset New Login Credentials "

    val message = "Your New Login Details are Username: " + user.email + " And the Password: " + generatedPassword + "" +
      "</p> You can access the Site  Provided to you By the Provider. " +
      "<b>PLEASE REMEMBER TO CHANGE YOUR PASSWORD</b><p/>" +
      "We are Sure your Superiors have told you that Great Powers Come with Great Responsibility"
    EmailMessage(subject, user.email, message)
  }

  override def createNewAccountMessage(user: User, password: String): EmailMessage = {
    val subject = " New Account Created For You"
    val message = "Your Login Details are Username: " + user.email + " And the Password: " + password + "" +
      "</p> You can access the Site  Provided to you By the Provider. " +
      "<b>PLEASE REMEMBER TO CHANGE YOUR PASSWORD</b><p/>" +
      "We are Sure your Superiors have told you that Great Powers Come with Great Responsibility"
    EmailMessage(subject, user.email, message)
  }

  override def resetPasswordMessage(user: User, password: String): EmailMessage = {
    val subject = "Your Reset New Login Credentials "

    val message = "Your New Login Details are Username: " + user.email + " And the Password: " + password + "" +
      "</p> You can access the Site  Provided to you By the Provider. " +
      "<b>PLEASE REMEMBER TO CHANGE YOUR PASSWORD</b><p/>" +
      "We are Sure your Superiors have told you that Great Powers Come with Great Responsibility"
    EmailMessage(subject, user.email, message)

  }

  override def forgetPasswordLinkMessage(user: User, resetKey: String, siteUrl: String): EmailMessage = {
    EmailMessage("Password Reset Request Link", user.email, "A request was Made to Reset Your Password. If you did not " +
      "Request the Reset, Please ignore the message, otherwise click on the Link Below  to request a New Password <p>" +
      " " + siteUrl + "/" + resetKey)
  }
}

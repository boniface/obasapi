package services.login.Impl

import domain.login.LoginToken
import io.jsonwebtoken.Claims
import org.jose4j.jwt.NumericDate
import org.jose4j.jwt.consumer.{JwtConsumer, JwtConsumerBuilder, JwtContext}
import repository.login.LoginTokenRepository
import services.login.LoginTokenService

import scala.concurrent.Future

class LoginTokenServiceImpl extends LoginTokenService {
  lazy val jwtConsumer: JwtConsumer = new JwtConsumerBuilder()
    .setSkipAllValidators()
    .setDisableRequireSignature()
    .setSkipSignatureVerification()
    .build()

  override def isTokenValid(token: String): Boolean = {
    def jwtContext: JwtContext = jwtConsumer.process(token)

    jwtContext.getJwtClaims.getExpirationTime.isAfter(NumericDate.now())
  }

  override def getUserEmail(token: String): String = {
    def jwtContext: JwtContext = jwtConsumer.process(token)

    jwtContext.getJwtClaims.getClaimValue(Claims.USER_EMAIL).toString
  }

  override def getUserHashedAgent(token: String): String = {
    def jwtContext: JwtContext = jwtConsumer.process(token)

    jwtContext.getJwtClaims.getClaimValue(Claims.USER_HASHAGENT).toString
  }

  override def getUserRole(token: String): String = {
    def jwtContext: JwtContext = jwtConsumer.process(token)

    jwtContext.getJwtClaims.getClaimValue(Claims.USER_ROLE).toString
  }

  override def getUserSiteId(token: String): String = {
    def jwtContext: JwtContext = jwtConsumer.process(token)

    jwtContext.getJwtClaims.getClaimValue(Claims.USER_SITEID).toString
  }

  override def getUserUserId(token: String): String = {
    def jwtContext: JwtContext = jwtConsumer.process(token)

    jwtContext.getJwtClaims.getClaimValue(Claims.USER_USERID).toString
  }

  override def saveEntity(entity: LoginToken): Future[Boolean] = {
    LoginTokenRepository.apply.saveEntity(entity)
  }

  override def getEntities: Future[Seq[LoginToken]] = {
    LoginTokenRepository.apply.getEntities
  }

  override def getEntity(id: String): Future[Option[LoginToken]] = {
    LoginTokenRepository.apply.getEntity(id)
  }

  override def deleteEntity(entity: LoginToken): Future[Boolean] = {
    LoginTokenRepository.apply.deleteEntity(entity)
  }
  override def createTable: Future[Boolean] = {
    LoginTokenRepository.apply.createTable
  }

}

package services.login.Impl

import domain.login.LoginToken
import domain.util.events.Claims
import org.jose4j.jwt.NumericDate
import org.jose4j.jwt.consumer.{JwtConsumer, JwtConsumerBuilder, JwtContext}
import repository.login.LoginTokenRepository
import services.login.LoginTokenService

import scala.concurrent.Future
import scala.util.Try

class LoginTokenServiceImpl extends LoginTokenService {

  lazy val jwtConsumer: JwtConsumer = new JwtConsumerBuilder()
    .setSkipAllValidators()
    .setDisableRequireSignature()
    .setSkipSignatureVerification()
    .build()

  override def isTokenValid(token: String): Either[Throwable,Boolean] = Try {
    def jwtContext: JwtContext = jwtConsumer.process(token)

    jwtContext.getJwtClaims.getExpirationTime.isAfter(NumericDate.now())
  }.toEither

  override def getUserEmail(token: String): String = {
    def jwtContext: JwtContext = jwtConsumer.process(token)

    jwtContext.getJwtClaims.getClaimValue( Claims.USER_EMAIL).toString
  }


  override def getUserRole(token: String): String = {
    def jwtContext: JwtContext = jwtConsumer.process(token)

    jwtContext.getJwtClaims.getClaimValue(Claims.USER_ROLE).toString
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

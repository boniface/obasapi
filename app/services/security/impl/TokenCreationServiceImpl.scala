package services.security.impl

import com.typesafe.config.ConfigFactory
import domain.security.ApiKeys
import domain.users.User
import org.jose4j.jws.{AlgorithmIdentifiers, JsonWebSignature}
import org.jose4j.jwt.JwtClaims
import services.security.{ApiKeysService, TokenCreationService}
import util.APPKeys

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TokenCreationServiceImpl extends TokenCreationService{
  val config = ConfigFactory.load()
  val time = config.getInt("key.inMinutes")

  override def generateLoginToken(user: User, role: String):  Future[String] = {
    val claims = new JwtClaims
    claims.setIssuer(APPKeys.ISSUER)
    claims.setAudience(APPKeys.SITEUSERS)
    claims.setExpirationTimeMinutesInTheFuture(time)
    claims.setGeneratedJwtId()
    claims.setIssuedAtToNow()
    claims.setNotBeforeMinutesInThePast(2)
    claims.setSubject(APPKeys.SITEACCESS)
    claims.setClaim(APPKeys.EMAIL, user.email)
    claims.setClaim(APPKeys.ROLE, role)
    getTokenStringFromClaims(claims)
  }

  private  def getTokenStringFromClaims(claims: JwtClaims): Future[String] = {
    for {
      publickKey: Option[ApiKeys] <- ApiKeysService.apply.getEntity(APPKeys.PUBLICKEY)
    } yield {
      val senderJwk = ApiKeysService.apply.getPublicJsonWebKey(publickKey)
      val jws = new JsonWebSignature
      jws.setPayload(claims.toJson)
      jws.setKey(senderJwk.getPrivateKey)
      jws.setKeyIdHeaderValue(senderJwk.getKeyId)
      jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.ECDSA_USING_P256_CURVE_AND_SHA256)
      jws.getCompactSerialization
    }
  }
}

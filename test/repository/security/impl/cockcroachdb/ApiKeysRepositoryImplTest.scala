package repository.security.impl.cockcroachdb

import java.time.{LocalDate, LocalDateTime}

import domain.security.ApiKeys
import org.jose4j.base64url.Base64Url
import org.scalatest.FunSuite
import repository.security.ApiKeysRepository
import util.APPKeys

import scala.concurrent.Await
import scala.concurrent.duration._

class ApiKeysRepositoryImplTest extends FunSuite {

  val kValue = Base64Url.encodeUtf8ByteRepresentation("TEST_VALUE")
  val xValue = Base64Url.encodeUtf8ByteRepresentation("x_value")
  val yValue = Base64Url.encodeUtf8ByteRepresentation("y_value")
  val dValue = Base64Url.encodeUtf8ByteRepresentation("d_value")
  val jwkJson = "{\"crv\":\"P-256\",\"kty\":\"EC\",\"x\":\"" + xValue + "\",\"y\":\"" + yValue + "\",\"k\":\""+ kValue +"\",\"d\":\"" + dValue + "\"}"

  val entity = ApiKeys(APPKeys.PUBLICKEY, jwkJson, APPKeys.ACTIVE, LocalDateTime.now)
  val repository = ApiKeysRepository

  test("testGetEntity") {

  }

  test("testSaveEntity") {
    val result = Await.result(repository.apply.saveEntity(entity), 2.minutes)
    assert(result.nonEmpty)
  }

  test("testDeleteEntity") {

  }

  test("testGetEntities") {

  }

}

package services.security.impl.cockcroachdb

import java.time.LocalDateTime

import domain.security.ApiKeys
import org.scalatest.FunSuite
import services.security.ApiKeysService
import util.APPKeys

import scala.concurrent.Await
import scala.concurrent.duration._

class ApiKeysServiceImplTest extends FunSuite {

  val service = ApiKeysService
  val phrase = "TEST_PHRASE"

  test("testSaveEntity") {
    val key = service.apply.generateJsonPublicKey(phrase)
    val entity = ApiKeys(APPKeys.PUBLICKEY, key, APPKeys.ACTIVE, LocalDateTime.now)
    val result = Await.result(service.apply.saveEntity(entity), 2.minutes)
    println(result)
    assert(result.nonEmpty)

  }

  test("testDeleteEntity") {

  }

  test("testGetPublicJsonWebKey") {

  }

  test("testGenerateResetToken") {

  }

  test("testGetEntity") {

  }

  test("testGenerateJsonPublicKey") {
    val result = service.apply.generateJsonPublicKey(phrase)
    assert(!result.isEmpty)

  }

  test("testGetEntities") {

  }

}

package repository.security

import java.time.LocalDateTime

import domain.security.ApiKeys
import org.scalatest.FunSuite

import services.security.ApiKeysService
import util.APPKeys

import scala.concurrent.Await
import scala.concurrent.duration._

class ApiKeysRepositoryImplTest extends FunSuite {

  val repository = ApiKeysRepository

  test("testGetEntity") {

  }

  test("testSaveEntity") {
    val service = ApiKeysService
    val phrase = "TEST_PHRASE"
    val key = service.apply.generateJsonPublicKey(phrase)
    val entity = ApiKeys(APPKeys.PUBLICKEY, key, APPKeys.ACTIVE, LocalDateTime.now)
    val result = Await.result(repository.apply.saveEntity(entity), 2.minutes)
    assert(result.nonEmpty)
  }

  test("testDeleteEntity") {

  }

  test("testGetEntities") {

  }

}

package repository.mail

import java.time.LocalDateTime

import domain.mail.MailConfig
import org.scalatest.FunSuite
import repository.mail.MailConfigRepository

import scala.concurrent.Await
import scala.concurrent.duration._

class MailConfigRepositoryTest extends FunSuite {
  val entity = MailConfig("1", "1", "11", "eeee", "host", "908", "active", LocalDateTime.now)
  val repository = MailConfigRepository

  test("createEntity") {
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity") {
    val result = Await.result(repository.apply.getEntity(entity.id), 2 minutes)
    assert(result.head.id == entity.id)
  }

  test("readEntities") {
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities") {
    val updatedEntity = entity.copy(host = "Home")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.id), 2 minutes)
    assert(result.head.host == updatedEntity.host)
  }


  test("deleteEntities") {
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.id), 2 minutes)
    assert(result.isEmpty)

  }


}

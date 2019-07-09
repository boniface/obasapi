package services.mail

import domain.mail.SmtpConfig
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class SmtpConfigServiceTest extends FunSuite{
  val entity = SmtpConfig("1",20,"test","hello","hest")
  val service = SmtpConfigService
  test("createEntity"){
    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(service.roach.getEntity(entity.id), 2 minutes)
    assert(result.head.id==entity.id)
  }

  test("createEntities"){
    val result = Await.result(service.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(port = 2018)
    Await.result(service.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.id), 2 minutes)
    assert(result.head.port==updatedEntity.port)
  }


  test("deleteEntities"){
    Await.result(service.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.roach.getEntity(entity.id), 2 minutes)
    assert(result.isEmpty)

  }
}

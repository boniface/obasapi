package repositories.users

import domain.users.UserInstitution
import org.scalatest.FunSuite
import repository.users.UserInstitutionRepository

import scala.concurrent.Await
import scala.concurrent.duration._


class UserInstitutionRepositoryTest extends FunSuite{
  val entity = UserInstitution("1","JvR")
  val repository = UserInstitutionRepository
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.userInstitutionId), 2 minutes)
    assert(result.head.userInstitutionId==entity.userInstitutionId)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(name = "JvR High")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userInstitutionId), 2 minutes)
    assert(result.head.name==updatedEntity.name)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userInstitutionId), 2 minutes)
    assert(result.isEmpty)

  }
}
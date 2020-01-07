package repository.security.impl.cockroach

import domain.security.ApiKeys
import repository.security.ApiKeysRepository
import repository.security.impl.cockroach.tables.ApiKeysTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApiKeysRepositoryImpl extends ApiKeysRepository {

  override def saveEntity(entity: ApiKeys): Future[Option[ApiKeys]] = {
    ApiKeysTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[ApiKeys]] = {
    ApiKeysTable.getEntities
  }

  override def getEntity(id: String): Future[Option[ApiKeys]] = {
    ApiKeysTable.getEntity(id)
  }

  override def deleteEntity(entity: ApiKeys): Future[Boolean] = {
    ApiKeysTable.deleteEntity(entity.id)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(ApiKeysTable.createTable)
  }

}



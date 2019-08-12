package repository.security.impl.cockcroachdb

import domain.security.ApiKeys
import repository.security.impl.cockcroachdb.tables.ApiKeysTable
import repository.security.ApiKeysRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

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



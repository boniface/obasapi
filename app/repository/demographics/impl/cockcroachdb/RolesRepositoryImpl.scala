package repository.demographics.impl.cockcroachdb

import domain.demographics.Roles
import repository.demographics.RolesRepository
import repository.demographics.impl.cockcroachdb.tables.RolesTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class RolesRepositoryImpl extends RolesRepository {
  override def saveEntity(entity: Roles): Future[Option[Roles]] = {
    RolesTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Roles]] = {
    RolesTable.getEntities
  }

  override def getEntity(id: String): Future[Option[Roles]] ={
    RolesTable.getEntity(id)
  }

  override def deleteEntity(entity: Roles): Future[Boolean] = {
    RolesTable.deleteEntity(entity.id)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(RolesTable.createTable)
  }
}



package repository.demographics.impl.cockcroachdb

import domain.demographics.Roles
import repository.demographics.impl.cockcroachdb.tables.RolesTable
import repository.demographics.RolesRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class RolesRepositoryImpl extends RolesRepository {
  override def saveEntity(entity: Roles): Future[Boolean] ={
    RolesTable.saveEntity(entity).map(value=> value.equals(entity))
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



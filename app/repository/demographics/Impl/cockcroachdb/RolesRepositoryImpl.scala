package repository.demographics.Impl.cockcroachdb

import domain.demographics.Roles
import repository.demographics.Impl.cockcroachdb.tables.RolesTable
import repository.demographics.RolesRepository

import scala.concurrent.Future

class RolesRepositoryImpl extends RolesRepository {
  override def saveEntity(entity: Roles): Future[Boolean] ={
    Future.successful( RolesTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[Roles]] = {
     RolesTable.getEntities
  }

  override def getEntity(id: String): Future[Option[Roles]] ={
     RolesTable.getEntity(id)
  }

  override def deleteEntity(entity: Roles): Future[Boolean] = {
    Future.successful( RolesTable.deleteEntity(entity.id).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful( RolesTable.createTable)
  }
}

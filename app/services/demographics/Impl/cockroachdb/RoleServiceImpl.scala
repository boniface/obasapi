package services.demographics.Impl.cockroachdb

import domain.demographics.Roles
import services.demographics.RoleService

import scala.concurrent.Future

class RoleServiceImpl extends RoleService{

  override def saveEntity(entity: Roles): Future[Boolean] = {
    RoleService.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Roles]] = {
    RoleService.roach.getEntities
  }

  override def getEntity(id: String): Future[Option[Roles]] = {
    RoleService.roach.getEntity(id)
  }

  override def deleteEntity(entity: Roles): Future[Boolean] = {
    RoleService.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    RoleService.roach.createTable
  }
}

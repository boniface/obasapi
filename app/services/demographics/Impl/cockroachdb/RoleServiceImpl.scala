package services.demographics.Impl.cockroachdb

import domain.demographics.Roles
import repository.demographics.RolesRepository
import services.demographics.RoleService

import scala.concurrent.Future

class RoleServiceImpl extends RoleService{

  override def saveEntity(entity: Roles): Future[Option[Roles]] =
    RolesRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[Roles]] = {
    RolesRepository.roach.getEntities
  }

  override def getEntity(id: String): Future[Option[Roles]] = {
    RolesRepository.roach.getEntity(id)
  }

  override def deleteEntity(entity: Roles): Future[Boolean] = {
    RolesRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    RolesRepository.roach.createTable
  }
}

package repository.demographics.Impl.cockcroachdb

import domain.demographics.Roles

import repository.demographics.RolesRepository

import scala.concurrent.Future

class RolesRepositoryImpl extends RolesRepository {
  override def saveEntity(entity: Roles): Future[Boolean] ={
    RolesRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Roles]] = {
     RolesRepository.roach.getEntities
  }

  override def getEntity(id: String): Future[Option[Roles]] ={
    RolesRepository.roach.getEntity(id)
  }

  override def deleteEntity(entity: Roles): Future[Boolean] = {
    RolesRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    RolesRepository.roach.createTable
  }
}

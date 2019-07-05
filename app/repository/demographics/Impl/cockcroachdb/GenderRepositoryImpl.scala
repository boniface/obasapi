package repository.demographics.Impl.cockcroachdb

import domain.demographics.Gender
import repository.demographics.GenderRepository


import scala.concurrent.Future

class GenderRepositoryImpl extends GenderRepository{
  override def saveEntity(entity: Gender): Future[Boolean] = {
  GenderRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Gender]] ={
    GenderRepository.roach.getEntities
  }

  override def getEntity(genderId: String): Future[Option[Gender]] = {
    GenderRepository.roach.getEntity(genderId)
  }

  override def deleteEntity(entity: Gender): Future[Boolean] = {
    GenderRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    GenderRepository.roach.createTable
  }
}

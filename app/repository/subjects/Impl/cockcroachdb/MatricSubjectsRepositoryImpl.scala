package repository.subjects.Impl.cockcroachdb

import domain.subjects.MatricSubjects
import repository.subjects.MatricSubjectsRepository

import scala.concurrent.Future


class MatricSubjectsRepositoryImpl extends MatricSubjectsRepository{
  override def saveEntity(entity: MatricSubjects): Future[Boolean] = {
   MatricSubjectsRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[MatricSubjects]] = {
   MatricSubjectsRepository.roach.getEntities
  }

  override def getEntity( subjectCode: String): Future[Option[MatricSubjects]] = {
    MatricSubjectsRepository.roach.getEntity(subjectCode)
  }

  override def deleteEntity(entity: MatricSubjects): Future[Boolean] = {
   MatricSubjectsRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
   MatricSubjectsRepository.roach.createTable
  }
}

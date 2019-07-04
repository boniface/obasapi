package repository.subjects.Impl.cockcroachdb

import domain.subjects.MatricSubjects

import repository.subjects.MatricSubjectsRepository

import scala.concurrent.Future


class MatricSubjectsRepositoryImpl extends MatricSubjectsRepository{
  override def saveEntity(entity: MatricSubjects): Future[Boolean] = {
    Future.successful(MatricSubjectsTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[MatricSubjects]] = {
    MatricSubjectsTable.getEntities
  }

  override def getEntity( subjectCode: String): Future[Option[MatricSubjects]] = {
    MatricSubjectsTable.getEntity( subjectCode)
  }

  override def deleteEntity(entity: MatricSubjects): Future[Boolean] = {
    Future.successful(MatricSubjectsTable.deleteEntity(entity. subjectCode).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(MatricSubjectsTable.createTable)
  }
}

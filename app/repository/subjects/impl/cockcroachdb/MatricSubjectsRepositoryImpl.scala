package repository.subjects.impl.cockcroachdb

import domain.subjects.MatricSubjects
import repository.subjects.MatricSubjectsRepository
import repository.subjects.impl.cockcroachdb.tables.MatricSubjectsTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


class MatricSubjectsRepositoryImpl extends MatricSubjectsRepository{
  override def saveEntity(entity: MatricSubjects): Future[Option[MatricSubjects]] = {
    MatricSubjectsTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[MatricSubjects]] = {
    MatricSubjectsTable.getEntities
  }

  override def getEntity( subjectCode: String): Future[Option[MatricSubjects]] = {
    MatricSubjectsTable.getEntity(subjectCode)
  }

  override def deleteEntity(entity: MatricSubjects): Future[Boolean] = {
    MatricSubjectsTable.deleteEntity(entity.subjectCode)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(MatricSubjectsTable.createTable)
  }
}



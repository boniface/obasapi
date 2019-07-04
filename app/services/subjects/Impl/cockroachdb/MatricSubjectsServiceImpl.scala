package services.subjects.Impl.cockroachdb

import domain.subjects.MatricSubjects
import services.subjects.MatricSubjectsService

import scala.concurrent.Future

class MatricSubjectsServiceImpl extends MatricSubjectsService{


  override def saveEntity(entity: MatricSubjects): Future[Boolean] = {
    MatricSubjectsService.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[MatricSubjects]] = {
    MatricSubjectsService.roach.getEntities
  }

  override def getEntity(subjectCode: String): Future[Option[MatricSubjects]] = {
    MatricSubjectsService.roach.getEntity(subjectCode)
  }

  override def deleteEntity(entity: MatricSubjects): Future[Boolean] = {
    MatricSubjectsService.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    MatricSubjectsService.roach.createTable
  }
}

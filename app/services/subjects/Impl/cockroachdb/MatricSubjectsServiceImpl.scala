package services.subjects.Impl.cockroachdb

import domain.subjects.MatricSubjects
import repository.subjects.MatricSubjectsRepository
import services.subjects.MatricSubjectsService

import scala.concurrent.Future

class MatricSubjectsServiceImpl extends MatricSubjectsService{


  override def saveEntity(entity: MatricSubjects): Future[Option[MatricSubjects]] =
    MatricSubjectsRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[MatricSubjects]] = {
    MatricSubjectsRepository.roach.getEntities
  }

  override def getEntity(courseCode: String): Future[Option[MatricSubjects]] = {
    MatricSubjectsRepository.roach.getEntity(courseCode)
  }

  override def deleteEntity(entity: MatricSubjects): Future[Boolean] = {
    MatricSubjectsRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    MatricSubjectsRepository.roach.createTable
  }
}

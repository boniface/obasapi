package services.subjects.Impl

import domain.subjects.MatricSubjects
import services.subjects.MatricSubjectsService
import scala.concurrent.Future

class MatricSubjectsServiceImpl extends MatricSubjectsService{


  override def saveEntity(entity: MatricSubjects): Future[Boolean] = ???

  override def getEntities: Future[Seq[MatricSubjects]] = ???

  override def getEntity(subjectCode: String): Future[Option[MatricSubjects]] = ???

  override def deleteEntity(entity: MatricSubjects): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}

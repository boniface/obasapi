package services.subjects.Impl

import domain.subjects.MatricSubjects
import services.CrudService

import scala.concurrent.Future

class MatricSubjectsServiceImpl extends CrudService[MatricSubjects]{


  override def saveEntity(entity: MatricSubjects): Future[Boolean] = ???

  override def getEntities: Future[Seq[MatricSubjects]] = ???

  override def getEntity(subjectCode: String): Future[Option[MatricSubjects]] = ???

  override def deleteEntity(entity: MatricSubjects): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}

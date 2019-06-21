package services.institutions.Impl

import domain.institutions.School
import services.institutions.SchoolService

import scala.concurrent.Future

class SchoolServiceImpl extends SchoolService {

  override def saveEntity(entity: School): Future[Boolean] = ???

  override def getEntities: Future[Seq[School]] = ???

  override def getEntity(id: String): Future[Option[School]] = ???

  override def deleteEntity(entity: School): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???

}
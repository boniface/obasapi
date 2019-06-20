package services.institutions.Impl

import domain.institutions.University
import services.institutions.UniversityService

import scala.concurrent.Future

class UniversityServiceImpl extends UniversityService {

  override def saveEntity(entity: University): Future[Boolean] = ???

  override def getEntities: Future[Seq[University]] = ???

  override def getEntity(id: String): Future[Option[University]] = ???

  override def deleteEntity(entity: University): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???

}

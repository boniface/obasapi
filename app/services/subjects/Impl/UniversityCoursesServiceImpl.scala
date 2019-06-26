package services.subjects.Impl

import domain.subjects.UniversityCourses
import services.CrudService

import scala.concurrent.Future

class UniversityCoursesServiceImpl extends CrudService[UniversityCourses]{

  override def saveEntity(entity: UniversityCourses): Future[Boolean] = ???

  override def getEntities: Future[Seq[UniversityCourses]] = ???

  override def getEntity(courseCode: String): Future[Option[UniversityCourses]] = ???

  override def deleteEntity(entity: UniversityCourses): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}

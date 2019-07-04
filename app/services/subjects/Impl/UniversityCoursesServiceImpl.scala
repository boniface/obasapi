package services.subjects.Impl

import domain.subjects.UniversityCourses
import services.subjects.UniversityCoursesService

import scala.concurrent.Future

class UniversityCoursesServiceImpl extends UniversityCoursesService{

  override def saveEntity(entity: UniversityCourses): Future[Boolean] = ???

  override def getEntities: Future[Seq[UniversityCourses]] = ???

  override def getEntity(courseCode: String): Future[Option[UniversityCourses]] = ???

  override def deleteEntity(entity: UniversityCourses): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}

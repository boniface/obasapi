package services.subjects.Impl.cockroachdb

import domain.subjects.UniversityCourses
import services.subjects.UniversityCoursesService

import scala.concurrent.Future

class UniversityCoursesServiceImpl extends UniversityCoursesService{

  override def saveEntity(entity: UniversityCourses): Future[Boolean] = {
    UniversityCoursesService.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UniversityCourses]] = {
    UniversityCoursesService.roach.getEntities
  }

  override def getEntity(courseCode: String): Future[Option[UniversityCourses]] = {
    UniversityCoursesService.roach.getEntity(courseCode)
  }

  override def deleteEntity(entity: UniversityCourses): Future[Boolean] = {
    UniversityCoursesService.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UniversityCoursesService.roach.createTable
  }
}

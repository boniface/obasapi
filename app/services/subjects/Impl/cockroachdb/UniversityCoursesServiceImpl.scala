package services.subjects.Impl.cockroachdb

import domain.subjects.UniversityCourses
import repository.subjects.UniversityCoursesRepository
import services.subjects.UniversityCoursesService

import scala.concurrent.Future

class UniversityCoursesServiceImpl extends UniversityCoursesService{

  override def saveEntity(entity: UniversityCourses): Future[Option[UniversityCourses]] =
    UniversityCoursesRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UniversityCourses]] = {
    UniversityCoursesRepository.roach.getEntities
  }

  override def getEntity(courseCode: String): Future[Option[UniversityCourses]] = {
    UniversityCoursesRepository.roach.getEntity(courseCode)
  }

  override def deleteEntity(entity: UniversityCourses): Future[Boolean] = {
    UniversityCoursesRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UniversityCoursesRepository.roach.createTable
  }
}

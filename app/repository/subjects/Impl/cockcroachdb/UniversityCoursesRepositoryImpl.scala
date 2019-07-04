package repository.subjects.Impl.cockcroachdb

import domain.subjects.UniversityCourses

import repository.subjects.UniversityCoursesRepository

import scala.concurrent.Future

class UniversityCoursesRepositoryImpl extends UniversityCoursesRepository {
  override def saveEntity(entity: UniversityCourses): Future[Boolean] ={
   UniversityCoursesRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UniversityCourses]] = {
   UniversityCoursesRepository.roach.getEntities
  }

  override def getEntity(courseCode: String): Future[Option[UniversityCourses]] ={
   UniversityCoursesRepository.roach.getEntity(courseCode)
  }

  override def deleteEntity(entity: UniversityCourses): Future[Boolean] = {
    UniversityCoursesRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UniversityCoursesRepository.roach.createTable
  }
}

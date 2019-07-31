package repository.subjects.impl.cockcroachdb

import domain.subjects.UniversityCourses
import repository.subjects.UniversityCoursesRepository
import repository.subjects.impl.cockcroachdb.tables.UniversityCoursesTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UniversityCoursesRepositoryImpl extends UniversityCoursesRepository {
  override def saveEntity(entity: UniversityCourses): Future[Option[UniversityCourses]] = {
    UniversityCoursesTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UniversityCourses]] = {
    UniversityCoursesTable.getEntities
  }

  override def getEntity(courseCode: String): Future[Option[UniversityCourses]] ={
    UniversityCoursesTable.getEntity(courseCode)
  }

  override def deleteEntity(entity: UniversityCourses): Future[Boolean] = {
    UniversityCoursesTable.deleteEntity(entity.courseCode)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UniversityCoursesTable.createTable)
  }
}



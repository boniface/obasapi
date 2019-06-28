package repository.subjects.Impl.cockcroachdb

import domain.subjects.UniversityCourses
import repository.subjects.UniversityCoursesRepository

import scala.concurrent.Future

class UniversityCoursesRepositoryImpl extends UniversityCoursesRepository {
  override def saveEntity(entity: UniversityCourses): Future[Boolean] ={
    Future.successful(UniversityCoursesTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UniversityCourses]] = {
    UniversityCoursesTable.getEntities
  }

  override def getEntity(courseCode: String): Future[Option[UniversityCourses]] ={
    UniversityCoursesTable.getEntity(courseCode)
  }

  override def deleteEntity(entity: UniversityCourses): Future[Boolean] = {
    Future.successful(UniversityCoursesTable.deleteEntity(entity.courseCode).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UniversityCoursesTable.createTable)
  }
}

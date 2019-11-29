package repository.academics

import domain.academics.CourseSubject


import repository.academics.tables.{CourseSubjectTable, CourseSubjectTableCreate}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class CourseSubjectRepositoryImpl extends CourseSubjectRepository{
  override def getEntity(courseId: String, subjectId: String): Future[Option[CourseSubject]] =
    CourseSubjectTable.getEntity(courseId, subjectId)

  override def getEntitiesForCourse(courseId: String): Future[Seq[CourseSubject]] =
    CourseSubjectTable.getEntitiesForCourse(courseId)

  override def saveEntity(entity: CourseSubject): Future[Option[CourseSubject]] =
    CourseSubjectTable.saveEntity(entity)

  override def getEntities: Future[Seq[CourseSubject]] =
    CourseSubjectTable.getEntities

  override def getEntity(id: String): Future[Option[CourseSubject]] = ???

  override def deleteEntity(entity: CourseSubject): Future[Boolean] =
    CourseSubjectTable.deleteEntity(entity.courseId, entity.subjectId).map(value => value.isValidInt)

  override def createTable: Future[Boolean] = Future.successful(CourseSubjectTableCreate.createTable)
}

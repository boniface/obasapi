package services.academics.Impl.cockroachdb

import domain.academics.CourseSubject
import repository.academics.CourseSubjectRepository
import services.academics.CourseSubjectService

import scala.concurrent.Future

class CourseSubjectServiceImpl extends CourseSubjectService {
  override def getEntity(courseId: String, subjectId: String): Future[Option[CourseSubject]] =
    CourseSubjectRepository.apply.getEntity(courseId, subjectId)

  override def getEntitiesForCourse(courseId: String): Future[Seq[CourseSubject]] =
    CourseSubjectRepository.apply.getEntitiesForCourse(courseId)

  override def saveEntity(entity: CourseSubject): Future[Option[CourseSubject]] =
    CourseSubjectRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[CourseSubject]] = CourseSubjectRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[CourseSubject]] = ???

  override def deleteEntity(entity: CourseSubject): Future[Boolean] = CourseSubjectRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = CourseSubjectRepository.apply.createTable
}

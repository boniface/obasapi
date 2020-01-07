package services.academics.Impl.cockroach

import domain.academics.Course
import repository.academics.CourseRepository
import services.academics.CourseService

import scala.concurrent.Future

class CourseServiceImpl extends CourseService {
  override def saveEntity(entity: Course): Future[Option[Course]] = CourseRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[Course]] = CourseRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[Course]] = CourseRepository.apply.getEntity(id)

  override def deleteEntity(entity: Course): Future[Boolean] = CourseRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = CourseRepository.apply.createTable
}

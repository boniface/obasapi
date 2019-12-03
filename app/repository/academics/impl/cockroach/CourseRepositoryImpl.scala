package repository.academics.impl.cockroach

import domain.academics.Course
import repository.academics.CourseRepository
import repository.academics.impl.cockroach.tables.CourseTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class CourseRepositoryImpl extends CourseRepository {
  override def saveEntity(entity: Course): Future[Option[Course]] =
    CourseTable.saveEntity(entity)

  override def getEntities: Future[Seq[Course]] = CourseTable.getEntities

  override def getEntity(id: String): Future[Option[Course]] = CourseTable.getEntity(id)

  override def deleteEntity(entity: Course): Future[Boolean] =
    CourseTable.deleteEntity(entity.id).map(value => value.isValidInt)

  override def createTable: Future[Boolean] =
    Future.successful(CourseTable.createTable)
}

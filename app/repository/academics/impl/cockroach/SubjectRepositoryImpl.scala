package repository.academics.impl.cockroach

import domain.academics.Subject
import repository.academics.SubjectRepository
import repository.academics.impl.cockroach.tables.SubjectTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class SubjectRepositoryImpl extends SubjectRepository {
  override def saveEntity(entity: Subject): Future[Option[Subject]] =
    SubjectTable.saveEntity(entity)

  override def getEntities: Future[Seq[Subject]] = SubjectTable.getEntities

  override def getEntity(id: String): Future[Option[Subject]] =
    SubjectTable.getEntity(id)

  override def deleteEntity(entity: Subject): Future[Boolean] =
    SubjectTable.deleteEntity(entity.id).map(value => value.isValidInt)

  override def createTable: Future[Boolean] =
    Future.successful(SubjectTable.createTable)
}

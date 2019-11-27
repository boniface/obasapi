package repository.academics

import domain.academics.Subject


import repository.academics.tables.SubjectTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

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

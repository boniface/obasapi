package services.academics.Impl.cockroachdb

import domain.academics.Subject
import repository.academics.SubjectRepository
import services.academics.SubjectService

import scala.concurrent.Future

class SubjectServiceImpl extends SubjectService {
  override def saveEntity(entity: Subject): Future[Option[Subject]] = SubjectRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[Subject]] = SubjectRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[Subject]] = SubjectRepository.apply.getEntity(id)

  override def deleteEntity(entity: Subject): Future[Boolean] = SubjectRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = SubjectRepository.apply.createTable
}

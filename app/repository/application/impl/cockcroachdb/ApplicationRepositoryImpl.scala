package repository.application.impl.cockcroachdb

import domain.application.Application
import repository.application.ApplicationRepository
import repository.application.impl.cockcroachdb.tables.ApplicationTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApplicationRepositoryImpl extends ApplicationRepository{
  override def saveEntity(entity: Application): Future[Option[Application]] =
    ApplicationTable.saveEntity(entity)

  override def getEntities: Future[Seq[Application]] =
    ApplicationTable.getEntities

  override def getEntity(id: String): Future[Option[Application]] =
    ApplicationTable.getEntity(id)

  override def deleteEntity(entity: Application): Future[Boolean] =
    ApplicationTable.deleteEntity(entity.id).map(value => value.isValidInt)

  override def createTable: Future[Boolean] =
    Future.successful(ApplicationTable.createTable)
}

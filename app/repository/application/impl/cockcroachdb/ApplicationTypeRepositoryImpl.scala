package repository.application.impl.cockcroachdb

import domain.application.ApplicationType
import repository.application.ApplicationTypeRepository
import repository.application.impl.cockcroachdb.tables.ApplicationTypeTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApplicationTypeRepositoryImpl extends ApplicationTypeRepository{
  override def saveEntity(entity: ApplicationType): Future[Option[ApplicationType]] =
    ApplicationTypeTable.saveEntity(entity)

  override def getEntities: Future[Seq[ApplicationType]] =
    ApplicationTypeTable.getEntities

  override def getEntity(id: String): Future[Option[ApplicationType]] =
    ApplicationTypeTable.getEntity(id)

  override def deleteEntity(entity: ApplicationType): Future[Boolean] =
    ApplicationTypeTable.deleteEntity(entity.id).map(value => value.isValidInt)

  override def createTable: Future[Boolean] = Future.successful(ApplicationTypeTable.createTable)
}

package repository.users.impl.cockroachdb

import domain.users.UserMatricInstitution
import repository.users.UserMatricInstitutionRepository
import repository.users.impl.cockroachdb.tables.UserMatricInstitutionTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserMatricInstitutionRepositoryImpl extends UserMatricInstitutionRepository{
  override def saveEntity(entity: UserMatricInstitution): Future[Option[UserMatricInstitution]] =
    UserMatricInstitutionTable.saveEntity(entity)

  override def getEntities: Future[Seq[UserMatricInstitution]] =
    UserMatricInstitutionTable.getEntities

  override def getEntity(id: String): Future[Option[UserMatricInstitution]] =
    UserMatricInstitutionTable.getEntity(id)

  override def deleteEntity(entity: UserMatricInstitution): Future[Boolean] =
    UserMatricInstitutionTable.deleteEntity(entity.userId).map(value => value.isValidInt)

  override def createTable: Future[Boolean] =
    Future.successful(UserMatricInstitutionTable.createTable)
}

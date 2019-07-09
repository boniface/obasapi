package repository.users.impl.cockroachdb

import domain.users.UserRole
import repository.users.impl.cockroachdb.tables.UserRoleTable
import repository.users.UserRoleRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserRoleRepositoryImpl  extends UserRoleRepository{

  override def saveEntity(entity: UserRole): Future[Boolean] = {
    UserRoleTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[UserRole]] = {
    UserRoleTable.getEntities
  }

  override def getEntity(userRoleId: String): Future[Option[UserRole]] = {
    UserRoleTable.getEntity(userRoleId)
  }

  override def deleteEntity(entity: UserRole): Future[Boolean] = {
    UserRoleTable.deleteEntity(entity.userRoleId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserRoleTable.createTable)
  }
}




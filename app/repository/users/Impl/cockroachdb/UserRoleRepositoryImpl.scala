package repository.users.Impl.cockroachdb

import domain.users.UserRole
import repository.users.Impl.cockroachdb.tables.UserRoleTable
import repository.users.UserRoleRepository

import scala.concurrent.Future

class UserRoleRepositoryImpl  extends UserRoleRepository{

  override def saveEntity(entity: UserRole): Future[Boolean] = {
    Future.successful(UserRoleTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserRole]] = {
    UserRoleTable.getEntities
  }

  override def getEntity(userRoleId: String): Future[Option[UserRole]] = {
    UserRoleTable.getEntity(userRoleId)
  }

  override def deleteEntity(entity: UserRole): Future[Boolean] = {
    Future.successful(UserRoleTable.deleteEntity(entity.userRoleId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserRoleTable.createTable)
  }
}

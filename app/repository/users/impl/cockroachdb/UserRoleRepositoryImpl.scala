package repository.users.impl.cockroachdb

import domain.users.UserRole
import repository.users.impl.cockroachdb.tables.UserRoleTable
import repository.users.UserRoleRepository

import scala.concurrent.Future

class UserRoleRepositoryImpl  extends UserRoleRepository{

  override def saveEntity(entity: UserRole): Future[Boolean] = {
    Future.successful(UserRoleTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserRole]] = {
    UserRoleTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserRole]] = {
    UserRoleTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserRole): Future[Boolean] = {
    Future.successful(UserRoleTable.deleteEntity(entity.userRoleId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserRoleTable.createTable)
  }
}

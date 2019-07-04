package repository.users.Impl.cockroachdb

import domain.users.UserRole
import repository.users.Impl.cockroachdb.tables.UserRoleTable
import repository.users.UserRoleRepository

import scala.concurrent.Future

class UserRoleRepositoryImpl  extends UserRoleRepository{

  override def saveEntity(entity: UserRole): Future[Boolean] = {
    UserRoleRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserRole]] = {
    UserRoleRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserRole]] = {
    UserRoleRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserRole): Future[Boolean] = {
    UserRoleRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UserRoleRepository.roach.createTable
  }
}

package repository.users.impl.cockroachdb

import domain.login.ChangePassword
import repository.users.UserChangePasswordRepository
import repository.users.impl.cockroachdb.tables.{UserChangePasswordTable, UserChangePasswordTableCreate}

import scala.concurrent.Future

class UserChangePasswordRepositoryImpl extends UserChangePasswordRepository{
  override def saveEntity(entity: ChangePassword): Future[Option[ChangePassword]] = UserChangePasswordTable.saveEntity(entity)

  override def getEntities: Future[Seq[ChangePassword]] = UserChangePasswordTable.getEntities

  override def getEntity(id: String): Future[Option[ChangePassword]] = UserChangePasswordTable.getEntity(id)

  override def deleteEntity(entity: ChangePassword): Future[Boolean] = ???

  override def createTable: Future[Boolean] = Future.successful(UserChangePasswordTableCreate.createTable)
}

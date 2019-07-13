package repository.login.impl.cockroachdb

import domain.login.LoginToken
import repository.login.LoginTokenRepository
import repository.login.impl.cockroachdb.tables.LoginTokenTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class LoginTokenRepositoryImpl extends LoginTokenRepository{

  override def saveEntity(entity: LoginToken): Future[Boolean] = {
    LoginTokenTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[LoginToken]] = {
    LoginTokenTable.getEntities
  }

  override def getEntity(email: String): Future[Option[LoginToken]] = {
    LoginTokenTable.getEntity(email)
  }

  override def deleteEntity(entity: LoginToken): Future[Boolean] = {
    LoginTokenTable.deleteEntity(entity.email)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(LoginTokenTable.createTable)
  }


}



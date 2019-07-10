package repository.users.impl.cockroachdb

import domain.users.User
import repository.users.impl.cockroachdb.tables.UserTable
import repository.users.UserRepository

import scala.concurrent.Future

class UserRepositoryImpl  extends UserRepository{
/*
  override def saveEntity(entity: User): Future[Boolean] = {
   // UserTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[User]] = {
    UserTable.getEntities
  }

  override def getEntity(email: String): Future[Option[User]] = {
    UserTable.getEntity(email)
  }

  override def deleteEntity(email: String): Future[Boolean] = {
   // UserTable.deleteEntity(entity.email)map(value=> value.)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserTable.createTable)
  }*/
    def createTable: scala.concurrent.Future[Boolean] = ???
    def deleteEntity(entity: domain.users.User): scala.concurrent.Future[Boolean] = ???
    def getEntities: scala.concurrent.Future[Seq[domain.users.User]] = ???
    def getEntity(id: String): scala.concurrent.Future[Option[domain.users.User]] = ???
    def saveEntity(entity: domain.users.User): scala.concurrent.Future[Boolean] = ???
}

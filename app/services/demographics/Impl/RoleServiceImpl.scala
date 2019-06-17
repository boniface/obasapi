package services.demographics.Impl

import domain.demographics.Roles
import services.demographics.RoleService

import scala.concurrent.Future

class RoleServiceImpl extends RoleService{

  override def saveEntity(entity: Roles): Future[Boolean] = ???

  override def getEntities: Future[Seq[Roles]] = ???

  override def getEntity(id: String): Future[Option[Roles]] = ???

  override def deleteEntity(entity: Roles): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}

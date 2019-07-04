package services.users.Impl.cockroachdb

import domain.users.UserResults
import services.users.UserResultsService

import scala.concurrent.Future

class UserResultsServiceImpl extends UserResultsService{

  override def saveEntity(entity: UserResults): Future[Boolean] =
    UserResultsService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserResults]] =
    UserResultsService.roach.getEntities

  override def getEntity(userAddressId: String): Future[Option[UserResults]] =
    UserResultsService.roach.getEntity(userAddressId)

  override def deleteEntity(entity: UserResults): Future[Boolean] =
    UserResultsService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserResultsService.roach.createTable

}
